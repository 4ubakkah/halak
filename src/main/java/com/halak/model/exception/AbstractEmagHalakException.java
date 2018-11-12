package com.halak.model.exception;

abstract class AbstractEmagHalakException extends RuntimeException {

    public AbstractEmagHalakException(String message, Object... args) {
        super(String.format(message, args));
    }
}
