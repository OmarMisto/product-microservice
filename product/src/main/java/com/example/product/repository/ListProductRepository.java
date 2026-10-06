package com.example.product.repository;

import com.example.product.model.Product;
import com.example.product.model.dto.ListProductCriteriaDto;

import java.util.List;

public interface ListProductRepository {
    public List<Product> findProductsByCriteria(ListProductCriteriaDto listProductCriteriaDto,List<Long> storeIds,int size);
}
