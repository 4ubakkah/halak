package com.halak.model.exception;

public class NonExistingPitIndexException extends AbstractEmagHalakException {

    public NonExistingPitIndexException(String message, Object... args) {
        super(message, args);
    }
}
