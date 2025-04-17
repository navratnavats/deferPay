package com.github.navratna.deferpay.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.github.navratna.deferpay.exception.DeferPayApiException.class)
    public ResponseEntity<ErrorResponse> handleDeferPayApiException(com.github.navratna.deferpay.exception.DeferPayApiException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(ex.getHttpStatus().value())
                .build();

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.github.navratna.deferpay.exception.ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("An unexpected error occurred")
                .status(500)
                .build();

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}