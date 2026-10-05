package com.example.product.controller.excption;

import lombok.RequiredArgsConstructor;


public class UpStreamException extends RuntimeException{
  public UpStreamException(String message){
      super(message);
  }
}
