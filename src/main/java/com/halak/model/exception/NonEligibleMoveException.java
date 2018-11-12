package com.halak.model.exception;

public class NonEligibleMoveException extends AbstractEmagHalakException {

    public NonEligibleMoveException(String message, Object... args) {
        super(message, args);
    }
}
