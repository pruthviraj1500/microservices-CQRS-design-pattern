package com.programming.dto;


import com.programming.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEvent {

    private String eventType;
    private Product product;

}
