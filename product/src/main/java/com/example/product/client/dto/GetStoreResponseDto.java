package com.example.product.client.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class GetStoreResponseDto {
    private long storeId;
    private String storeName;
    private String bio;
    private String email;
    private String phoneNumber;
    private String city;
    private String country;
}
