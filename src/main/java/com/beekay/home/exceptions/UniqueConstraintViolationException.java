package com.beekay.home.exceptions;

public class UniqueConstraintViolationException extends Exception {
    public UniqueConstraintViolationException() {
    }

    public UniqueConstraintViolationException(String message) {
        super(message);
    }

    public UniqueConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public UniqueConstraintViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
