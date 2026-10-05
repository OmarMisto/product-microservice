package com.example.product.controller;

import com.example.product.model.dto.CreateProductDto;
import com.example.product.model.dto.CreatedProductDto;
import com.example.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;
    @PostMapping("/create/product")
    public ResponseEntity<CreatedProductDto> createProductController(@RequestBody CreateProductDto createProductDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(iProductService.createProductService(createProductDto));
    }
}
