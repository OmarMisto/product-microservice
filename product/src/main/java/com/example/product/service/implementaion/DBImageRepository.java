package com.example.product.service.implementaion;

import com.example.product.model.Product;
import com.example.product.model.ProductImage;
import com.example.product.model.dto.DBPostedImageDto;
import com.example.product.model.dto.PostedImageDto;
import com.example.product.repository.ProductRepository;
import com.example.product.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DBImageRepository implements ImageRepository {
    private final ProductRepository productRepository;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<? extends PostedImageDto> saveAllImages(List<MultipartFile> images, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product not found"));
        images.forEach(multipartFile ->{
            try {
                product.getProductImages().add(ProductImage.builder()
                        .image(multipartFile.getBytes())
                        .contentType(multipartFile.getContentType())
                        .size(multipartFile.getSize())
                        .name(multipartFile.getName())
                        .build());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } );
        return productRepository.save(product).getProductImages()
                .stream()
                .map(productImage -> new DBPostedImageDto(productImage.getImage(),productImage.getProductImageId(),productImage.getContentType(),productImage.getName(),productImage.getSize()))
                .toList();
    }

    @Override
    public List<? extends PostedImageDto> findAllImagesById(long productId) {
        Product product= productRepository.findById(productId).orElse(null);
        if (product ==null){
            return List.of(new DBPostedImageDto());
        }
        return product.getProductImages().stream().map(productImage ->
                new DBPostedImageDto(productImage.getImage(),productImage.getProductImageId(),productImage.getContentType(),productImage.getName(),productImage.getSize()))
                .toList();
    }
}
