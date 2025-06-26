package com.programming.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEventResponse {
    private String eventType;
    private ProductResponse productResponse;
}
