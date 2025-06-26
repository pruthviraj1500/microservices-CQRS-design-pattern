package com.programming.service;

import com.programming.dto.ProductEvent;
import com.programming.response.ProductResponse;

public interface ProductCommandService {

    ProductResponse createProduct(ProductEvent productEvent);

    ProductResponse updateProduct(long id, ProductEvent productEvent);

}
