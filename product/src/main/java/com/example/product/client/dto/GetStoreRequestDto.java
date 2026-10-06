package com.example.product.client.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class GetStoreRequestDto {
    private String city;
    private String category;
    private int page;
}
