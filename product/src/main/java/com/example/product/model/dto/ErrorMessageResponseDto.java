package com.example.product.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ErrorMessageResponseDto {
    private int statusCode;
    private String status;
    private String message;
    private final LocalDateTime date=LocalDateTime.now();
}
