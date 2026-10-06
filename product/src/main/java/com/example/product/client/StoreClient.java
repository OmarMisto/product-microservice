package com.example.product.client;

import com.example.product.client.dto.GetStoreRequestDto;
import com.example.product.client.dto.GetStoreResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "store-service",url = "/api/v1/store/")
public interface StoreClient {
    @GetMapping("get/stores/")
    public List<GetStoreResponseDto> getStoreByCityAndCategory(@RequestBody GetStoreRequestDto getStoreRequestDto);
}
