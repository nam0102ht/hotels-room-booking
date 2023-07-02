package com.ntnn.booking.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalException extends RuntimeException {
    private String message;
    private Throwable throwable;

    public TechnicalException(String message, Throwable throwable) {
        super(message);
        this.addSuppressed(throwable);
    }
}
