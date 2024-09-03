package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception;

public class BrandAlreadyExistsException extends RuntimeException {
  public BrandAlreadyExistsException(String message) {
    super(message);
  }
}
