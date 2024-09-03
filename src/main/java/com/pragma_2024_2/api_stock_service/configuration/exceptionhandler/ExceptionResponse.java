package com.pragma_2024_2.api_stock_service.configuration.exceptionhandler;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private final String message;
    private final String status;
    private final LocalDateTime timestamp;

    public ExceptionResponse(String message, String status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

}
