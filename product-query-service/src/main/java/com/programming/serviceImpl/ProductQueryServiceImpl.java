package com.programming.serviceImpl;

import com.programming.custom_exception.ResourceNotFoundException;
import com.programming.model.Product;
import com.programming.repository.ProductRepository;
import com.programming.response.ProductEventResponse;
import com.programming.response.ProductResponse;
import com.programming.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;


    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @KafkaListener(topics = "product-topic-3", groupId = "product-group-3")
    public void processProductEvents(ProductEventResponse productEventResponse) {
        ProductResponse productResponse = productEventResponse.getProductResponse();
        Product product = mapper.map(productResponse, Product.class);
        if (productEventResponse.getEventType().equals("CreateProduct")) {
            productRepository.save(product);
        }
        if (productEventResponse.getEventType().equals("UpdateProduct")) {
            Product existingProduct = productRepository.findById(product.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found for id : "+product.getId()));
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setProductDescription(product.getProductDescription());
            productRepository.save(existingProduct);
        }
    }
}
