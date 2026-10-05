package com.example.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddProductRequestDto {
    private long stockId;
    private String productNo;
    private String brand;
    private int quantity;
    private BigDecimal unitPrice;
    private int discountPercentage;
    private boolean isAvailable;
}
