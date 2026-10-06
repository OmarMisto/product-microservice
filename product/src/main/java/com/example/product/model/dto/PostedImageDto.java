package com.example.product.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PostedImageDto {
   private long imageId;
   private String contentType;
   private String name;
   private long size;
}
