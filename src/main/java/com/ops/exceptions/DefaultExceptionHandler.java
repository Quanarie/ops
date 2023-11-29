package com.ops.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(OpsException.class)
    public ResponseEntity<ErrorDto> handleUserException(OpsException ex, WebRequest request) {
        ErrorDto errorDTO = new ErrorDto(ex.getLocalizedMessage(), ex.getExceptionCode());
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(errorDTO);
    }
}
