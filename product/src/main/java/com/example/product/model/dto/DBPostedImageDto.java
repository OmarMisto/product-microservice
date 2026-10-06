package com.example.product.model.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class DBPostedImageDto extends PostedImageDto{
    private byte[] bytes;
    public DBPostedImageDto(byte[] bytes,long imageId,String contentType,String name,long size){
       super(imageId,contentType,name,size);
       this.bytes=bytes;
    }

}
