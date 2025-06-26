package com.programming.controller;


import com.programming.response.ProductResponse;
import com.programming.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService queryService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(queryService.getProducts());
    }

}
