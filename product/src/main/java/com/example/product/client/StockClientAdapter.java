package com.example.product.client;

import com.example.product.client.dto.AddProductRequestDto;
import com.example.product.client.dto.AddProductStockResponseDto;
import com.example.product.controller.excption.UpStreamException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockClientAdapter implements StockPort {
    private final StockClient stockClient;

    @Override
    public AddProductStockResponseDto addProductToStock(AddProductRequestDto addProductRequestDto) {
        try {
            return stockClient.addProductToStock(addProductRequestDto);
        }catch (FeignException feignException){
            throw new UpStreamException("Add Stock Failed: "+feignException.getMessage());
        }
    }
}
