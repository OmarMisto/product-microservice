package com.example.product.service.implementaion;

import com.example.product.client.StockClient;
import com.example.product.client.StockClientAdapter;
import com.example.product.client.StockPort;
import com.example.product.client.dto.AddProductRequestDto;
import com.example.product.client.dto.AddProductStockResponseDto;
import com.example.product.model.Product;
import com.example.product.model.ProductCategory;
import com.example.product.model.dto.CreateProductDto;
import com.example.product.model.dto.CreatedProductDto;
import com.example.product.repository.ProductCategoryRepository;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private StockPort stockPort;
    @InjectMocks
    private ProductService productService;
    @Test
    void createProductService() {
        AddProductStockResponseDto mockAddProductStockResponseDto= AddProductStockResponseDto.builder().storeId(101).stockId(102).stockProductId(1987).productNo("n100b7").brand("Apple").quantity(30).unitPrice(BigDecimal.valueOf(999.9)).discountPercentage(10).isAvailable(true).build();
        Mockito.when(stockPort.addProductToStock(Mockito.any( AddProductRequestDto.class))).thenReturn(mockAddProductStockResponseDto);
        Optional<ProductCategory> mockProductCategory= Optional.ofNullable(ProductCategory.builder().categoryId(7).categoryName("Phone").products(null).build());
        Mockito.when(productCategoryRepository.findByCategoryName(Mockito.anyString())).thenReturn(mockProductCategory);
        ProductCategory mockProductCategory2= ProductCategory.builder().categoryId(7).categoryName("Phone").products(null).build();
        Product mockSavedProduct=Product.builder().productId(1902).productName("IPhone 17 pro").description("IPhone 17 pro smart phone").productCategory(mockProductCategory2).price(BigDecimal.valueOf(999.9)).discountPercentage(10).build();
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockSavedProduct);
        CreateProductDto createProductDto= CreateProductDto.builder().stockId(102).productNo("n100b7").name("IPhone 17 pro").description("IPhone 17 pro smart phone").categoryName("Phone").brand("Apple").quantity(30).unitPrice(BigDecimal.valueOf(999.9)).discountPercentage(10).isAvailable(true).build();
        CreateProductDto request = CreateProductDto.builder()
                .stockId(102)
                .productNo("n100b7")
                .brand("Apple")
                .quantity(30)
                .unitPrice(BigDecimal.valueOf(999.9))
                .discountPercentage(10)
                .categoryName("Phone")
                .name("IPhone 17 pro")
                .description("IPhone 17 pro smart phone")
                .build();
      CreatedProductDto createdProductDto= productService.createProductService(createProductDto);
    }
}