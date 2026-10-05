package com.example.product.client;

import com.example.product.client.dto.AddProductRequestDto;
import com.example.product.client.dto.AddProductStockResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-service")
public interface StockClient {
    @PostMapping("/api/v1/stock/add/product")
    public AddProductStockResponseDto addProductToStock(@RequestBody AddProductRequestDto addProductRequestDto);
}
