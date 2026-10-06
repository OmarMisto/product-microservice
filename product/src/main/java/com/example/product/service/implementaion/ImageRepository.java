package com.example.product.service.implementaion;


import com.example.product.model.dto.PostedImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageRepository {
    public List <? extends PostedImageDto > saveAllImages(List<MultipartFile> images, long productId);
}
