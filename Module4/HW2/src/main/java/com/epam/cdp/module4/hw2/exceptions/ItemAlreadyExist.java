package com.epam.cdp.module4.hw2.exceptions;

public class ItemAlreadyExist extends RuntimeException {

    public ItemAlreadyExist() {
    }

    public ItemAlreadyExist(String message) {
        super(message);
    }

    public ItemAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemAlreadyExist(Throwable cause) {
        super(cause);
    }

    public ItemAlreadyExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
