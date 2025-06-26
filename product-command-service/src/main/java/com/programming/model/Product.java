package com.programming.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "PRODUCT_COMMAND")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private Double price;

}
