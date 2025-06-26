package com.programming.service;


import com.programming.response.ProductResponse;

import java.util.List;

public interface ProductQueryService {
    List<ProductResponse> getProducts();
}
