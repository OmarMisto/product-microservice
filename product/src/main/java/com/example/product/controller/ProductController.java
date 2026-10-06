package com.example.product.controller;

import com.example.product.model.dto.CreateProductDto;
import com.example.product.model.dto.CreatedProductDto;
import com.example.product.model.dto.ListProductCriteriaDto;
import com.example.product.model.dto.ListProductDto;
import com.example.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;
    @PostMapping("/create/product")
    public ResponseEntity<CreatedProductDto> createProductController(@RequestBody CreateProductDto createProductDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(iProductService.createProductService(createProductDto));
    }
    @PostMapping("/post/product/images")
    public ResponseEntity<?>postProductImagesController(@RequestPart List<MultipartFile> images,@RequestParam(name = "productId")long productId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iProductService.postProductImagesService(images,productId));
    }

    @GetMapping("/list/products")
    public ResponseEntity<List<ListProductDto>> listProducts(@RequestBody ListProductCriteriaDto listProductCriteriaDto){
        return ResponseEntity.ok(iProductService.listProducts(listProductCriteriaDto));
    }

}
