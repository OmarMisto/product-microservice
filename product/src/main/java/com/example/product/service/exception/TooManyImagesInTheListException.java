package com.example.product.service.exception;

public class TooManyImagesInTheListException extends RuntimeException {
    public TooManyImagesInTheListException(String message){
        super(message);
    }
}
