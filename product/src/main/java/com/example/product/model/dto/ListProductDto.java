package com.example.product.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ListProductDto {
    private String city;
    private String country;
    private String productName;
    private String brand;
    private String category;
}
