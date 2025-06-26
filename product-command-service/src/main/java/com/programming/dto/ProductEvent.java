package com.programming.dto;


import com.programming.request.ProductRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEvent {

    @NotBlank(message = "EventType is required")
    private String eventType;
    private ProductRequest productRequest;

}
