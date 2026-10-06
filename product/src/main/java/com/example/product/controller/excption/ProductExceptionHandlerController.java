package com.example.product.controller.excption;

import com.example.product.model.dto.ErrorMessageResponseDto;
import com.example.product.service.exception.ProductNotFoundException;
import com.example.product.service.exception.TooManyImagesInTheListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandlerController {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessageResponseDto> productNotFoundExceptionHandlerController(ProductNotFoundException productNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageResponseDto
                        .builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .status("NOT_FOUND")
                        .message(productNotFoundException.getMessage())
                        .build());
    }
    @ExceptionHandler(TooManyImagesInTheListException.class)
    public ResponseEntity<ErrorMessageResponseDto>tooManyImagesInTheListException(TooManyImagesInTheListException tooManyImagesInTheListException){
        return ResponseEntity.badRequest()
                .body(ErrorMessageResponseDto
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status("BAD_REQUEST")
                .message(tooManyImagesInTheListException.getMessage())
                .build());
    }
}
