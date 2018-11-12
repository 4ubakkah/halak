package com.halak.api.impl;

import com.halak.model.dto.ErrorResponseDto;
import com.halak.model.exception.NonEligibleMoveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NonEligibleMoveException.class)
    protected ResponseEntity<Object> handleNonEligibleMove(RuntimeException ex, WebRequest request) {
        log.error("Error: {}", ex.getMessage(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage());
        return handleExceptionInternal(ex, errorResponseDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleAllTheRestRuntimeExceptions(RuntimeException ex, WebRequest request) {
        log.error("Error: {}", ex.getMessage(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage());
        return handleExceptionInternal(ex, errorResponseDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
