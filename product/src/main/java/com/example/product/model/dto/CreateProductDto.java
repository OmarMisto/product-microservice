package com.example.product.model.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateProductDto {
    private long stockId;
    private String productNo;
    private String name;
    private String description;
    private String categoryName;
    private String brand;
    private int quantity;
    private BigDecimal unitPrice;
    private int discountPercentage;
    private boolean isAvailable;
}
