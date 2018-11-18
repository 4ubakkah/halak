package com.halak.model.exception;

public class GameIsAlreadyCompleteException extends AbstractEmagHalakException {

    public GameIsAlreadyCompleteException(String message, Object... args) {
        super(message, args);
    }
}
