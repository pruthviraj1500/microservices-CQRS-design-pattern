package com.programming.serviceImpl;

import com.programming.custom_exception.ResourceNotFoundException;
import com.programming.dto.ProductEvent;
import com.programming.model.Product;
import com.programming.repository.ProductRepository;
import com.programming.request.ProductRequest;
import com.programming.response.ProductEventResponse;
import com.programming.response.ProductResponse;
import com.programming.service.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final ModelMapper modelMapper;


    @Override
    public ProductResponse createProduct(ProductEvent productEvent) {
        ProductRequest productRequest = productEvent.getProductRequest();
        Product product = modelMapper.map(productRequest, Product.class);
        Product persistProduct = productRepository.save(product);
        ProductResponse productResponse = modelMapper.map(persistProduct, ProductResponse.class);
//        ProductEvent event = new ProductEvent("CreateProduct", productRequest);
        ProductEventResponse productEventResponse = new ProductEventResponse(productEvent.getEventType(),productResponse);
        kafkaTemplate.send("product-topic-3", productEventResponse);
        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(long id, ProductEvent productEvent) {
        Product detachedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id : "+id));
        ProductRequest updatedProduct = productEvent.getProductRequest();
        if (updatedProduct.getProductName() != null) detachedProduct.setProductName(updatedProduct.getProductName());
        if (updatedProduct.getPrice() != null) detachedProduct.setPrice(updatedProduct.getPrice());
        if (updatedProduct.getProductDescription() != null) detachedProduct.setProductDescription(updatedProduct.getProductDescription());
        Product savedUpdatedProduct = productRepository.save(detachedProduct);
        ProductResponse productResponse = modelMapper.map(savedUpdatedProduct, ProductResponse.class);
        ProductEventResponse productEventResponse = new ProductEventResponse(productEvent.getEventType(), productResponse);
        kafkaTemplate.send("product-topic-3", productEventResponse);
        return productResponse;
    }
}
