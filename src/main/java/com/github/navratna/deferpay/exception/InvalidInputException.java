package com.github.navratna.deferpay.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidInputException extends DeferPayApiException {
    public InvalidInputException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
