package com.example.product.service.implementaion;

import com.example.product.client.StockClient;
import com.example.product.client.StoreClient;
import com.example.product.client.dto.AddProductRequestDto;
import com.example.product.client.dto.GetStoreRequestDto;
import com.example.product.client.dto.GetStoreResponseDto;
import com.example.product.model.Product;
import com.example.product.model.ProductCategory;
import com.example.product.model.Stock;
import com.example.product.client.dto.AddProductStockResponseDto;
import com.example.product.model.dto.*;
import com.example.product.repository.ListProductRepository;
import com.example.product.repository.ProductCategoryRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.service.IProductService;
import com.example.product.service.exception.CategoryNotFoundException;
import com.example.product.service.exception.TooManyImagesInTheListException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final StockClient stockClient;
    private final ListProductRepository listProductRepository;
    private final StoreClient storeClient;
    private final ImageRepository imageRepository;
    @Override
    @Transactional(rollbackFor = FeignException.FeignClientException.class)
    public CreatedProductDto createProductService(CreateProductDto createProductDto)  {
         AddProductStockResponseDto addProductStockResponseDto= stockClient.addProductToStock(AddProductRequestDto
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

    @Override
    public List<? extends PostedImageDto> postProductImagesService(List<MultipartFile> images, long productId) {
        if (images.size()>10){
            throw new TooManyImagesInTheListException("the images list contain more the 10 images");
        }
        return imageRepository.saveAllImages(images,productId);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = FeignException.class)
    public List<ListProductDto> listProducts(ListProductCriteriaDto listProductCriteriaDto) {
        List<Long> storeIds =storeClient.getStoreByCityAndCategory(GetStoreRequestDto
                .builder()
                .city(listProductCriteriaDto.getCity())
                .category(listProductCriteriaDto.getCategory())
                .page(listProductCriteriaDto.getPage())
                .build()).stream().map(GetStoreResponseDto::getStoreId).toList();
        PageRequest pageable = PageRequest.of(listProductCriteriaDto.getPage(),50);
        return listProductRepository
                .findProductsByCriteria(listProductCriteriaDto,storeIds, pageable.getPageSize())
                .stream().map(product -> ListProductDto.builder()
                        .productId(product.getProductId())
                        .storeId(product.getStock().getStoreId())
                        .productName(product.getProductName())
                        .description(product.getDescription())
                        .brand(product.getBrand())
                        .category(product.getProductCategory().getCategoryName())
                        .price(product.getPrice())
                        .discountPercentage(product.getDiscountPercentage())
                        .isAvailableInYourRegin(true)
                        .build()
                )
                .toList();
    }

}
