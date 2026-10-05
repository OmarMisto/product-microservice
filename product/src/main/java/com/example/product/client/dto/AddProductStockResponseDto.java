package com.example.product.client.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddProductStockResponseDto {
    private long storeId;
    private long stockId;
    private long stockProductId;
    private String productNo;
    private String brand;
    private int quantity;
    private BigDecimal unitPrice;
    private int discountPercentage;
    private boolean isAvailable;
}
