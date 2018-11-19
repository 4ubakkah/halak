package com.halak.model.exception;

public class GameDoesNotExistException extends AbstractEmagHalakException {

    public GameDoesNotExistException(String message, Object... args) {
        super(message, args);
    }
}
