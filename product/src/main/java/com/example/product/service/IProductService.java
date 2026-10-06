package com.example.product.service;

import com.example.product.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    public CreatedProductDto createProductService(CreateProductDto createProductDto);
    public List<? extends PostedImageDto> postProductImagesService(List<MultipartFile> images,long productId);
    public List<ListProductDto> listProducts(ListProductCriteriaDto listProductCriteriaDto);


}
