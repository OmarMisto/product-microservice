package com.example.product.client;

import com.example.product.client.dto.AddProductRequestDto;
import com.example.product.client.dto.AddProductStockResponseDto;
import org.springframework.stereotype.Component;


public interface StockPort {
    public AddProductStockResponseDto addProductToStock(AddProductRequestDto addProductRequestDto);
}
