package com.halak.model.exception;

public class GameDoesntExistException extends AbstractEmagHalakException {

    public GameDoesntExistException(String message, Object... args) {
        super(message, args);
    }
}
