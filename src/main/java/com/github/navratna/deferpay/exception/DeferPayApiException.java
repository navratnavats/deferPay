package com.github.navratna.deferpay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DeferPayApiException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}




