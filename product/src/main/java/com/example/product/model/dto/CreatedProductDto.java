package com.example.product.model.dto;

import com.example.product.model.Stock;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreatedProductDto {
    private long productId;
    private String productName;
    private String brand;
    private String description;
    private String productCategory;
    private BigDecimal price;
    private int discountPercentage;
}
