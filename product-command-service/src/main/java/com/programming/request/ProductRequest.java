package com.programming.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "ProductName is required")
    @Size(max = 50, message = "ProductName must not exceed 50 characters")
    private String productName;

    @NotBlank(message = "ProductDescription is required")
    @Size(max = 50, message = "ProductDescription must not exceed 50 characters")
    private String productDescription;

    @NotBlank(message = "Price is required")
    private Double price;
}
