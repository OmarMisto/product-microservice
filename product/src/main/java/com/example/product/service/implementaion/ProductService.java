package com.example.product.service.implementaion;

import com.example.product.client.StockClient;
import com.example.product.client.StockPort;
import com.example.product.client.dto.AddProductRequestDto;
import com.example.product.model.Product;
import com.example.product.model.ProductCategory;
import com.example.product.model.Stock;
import com.example.product.client.dto.AddProductStockResponseDto;
import com.example.product.model.dto.CreateProductDto;
import com.example.product.model.dto.CreatedProductDto;
import com.example.product.repository.ProductCategoryRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.service.IProductService;
import com.example.product.service.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final StockPort stockPort;
    @Override
    @Transactional
    public CreatedProductDto createProductService(CreateProductDto createProductDto) {
         AddProductStockResponseDto addProductStockResponseDto= stockPort.addProductToStock(AddProductRequestDto
                 .builder()
                 .stockId(createProductDto.getStockId())
                 .productNo(createProductDto.getProductNo())
                 .brand(createProductDto.getBrand())
                 .quantity(createProductDto.getQuantity())
                 .unitPrice(createProductDto.getUnitPrice())
                 .discountPercentage(createProductDto.getDiscountPercentage())
                 .isAvailable(createProductDto.isAvailable())
                 .build());
         ProductCategory productCategory=productCategoryRepository.findByCategoryName(createProductDto.getCategoryName()).orElseThrow(()->new CategoryNotFoundException("category not found"));
         Product savedProduct= productRepository.save(Product
                 .builder()
                        .productCategory(productCategory)
                        .productName(createProductDto.getName())
                        .stock(Stock.builder().productStockId(addProductStockResponseDto.getStockProductId())
                        .storeId(addProductStockResponseDto.getStoreId()).build())
                        .productName(createProductDto.getName())
                        .price(addProductStockResponseDto.getUnitPrice())
                        .discountPercentage(addProductStockResponseDto.getDiscountPercentage())
                        .description(createProductDto.getDescription())
                        .productNo(createProductDto.getProductNo())
                        .brand(createProductDto.getBrand())
                .build());
        return CreatedProductDto.builder()
                .productId(savedProduct.getProductId())
                .productName(savedProduct.getProductName())
                .brand(savedProduct.getBrand())
                .price(savedProduct.getPrice())
                .discountPercentage(savedProduct.getDiscountPercentage())
                .description(createProductDto.getDescription())
                .productCategory(savedProduct.getProductCategory().getCategoryName())
                .build();
    }
}
