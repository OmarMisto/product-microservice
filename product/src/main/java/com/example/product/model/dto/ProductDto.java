package com.example.product.model.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProductDto {
    private long productId;
    private long storeId;
    private String productName;
    private String description;
    private String productNo;
    private String brand;
    private String category;
    private BigDecimal price;
    private int discountPercentage;
}
