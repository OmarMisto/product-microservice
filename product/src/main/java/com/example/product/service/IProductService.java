package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.model.dto.CreateProductDto;
import com.example.product.model.dto.CreatedProductDto;
import com.example.product.model.dto.ListProductDto;

import java.util.List;

public interface IProductService {
    public CreatedProductDto createProductService(CreateProductDto createProductDto);

}
