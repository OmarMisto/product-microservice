package com.example.product.service;

import com.example.product.model.dto.CreateProductDto;
import com.example.product.model.dto.CreatedProductDto;

public interface IProductService {
    public CreatedProductDto createProductService(CreateProductDto createProductDto);

}
