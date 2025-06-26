package com.programming.controller;


import com.programming.dto.ProductEvent;
import com.programming.response.ProductResponse;
import com.programming.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductEvent productEvent) {
//        return productCommandService.createProduct(productEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCommandService.createProduct(productEvent));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent) {
//        return productCommandService.updateProduct(id, productEvent);
        return ResponseEntity.status(HttpStatus.OK).body(productCommandService.updateProduct(id, productEvent));
    }

}

