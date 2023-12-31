package com.ntnn.booking.controller;

import com.ntnn.booking.exception.TechnicalException;
import com.ntnn.booking.model.ErrorMessage;
import jakarta.validation.ValidationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler({TechnicalException.class})
    public ResponseEntity<ErrorMessage> handleTechnical(Exception exception) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .errorId(400)
                .message(exception.getSuppressed()[0].getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorMessage> handle500(Exception exception) {
        return new ResponseEntity<>(ErrorMessage.builder()
                    .errorId(500)
                    .message(exception.getSuppressed()[0].getMessage())
                    .build(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorMessage> handleValidate(Exception exception) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .errorId(400)
                .message(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    };
}
