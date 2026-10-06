package com.example.product.model.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ListProductCriteriaDto {
    private String productName;
    private String country;
    private String city;
    private String brand;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int page;
}
