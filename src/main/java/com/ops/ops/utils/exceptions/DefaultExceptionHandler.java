package com.ops.ops.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDto> handleCustomerException(CustomerException ex, WebRequest request) {
        ErrorDto errorDTO = new ErrorDto(ex.getLocalizedMessage(), ex.getExceptionCode());
        return buildResponseEntity(ex.getHttpStatus(), errorDTO);
    }

    private ResponseEntity<ErrorDto> buildResponseEntity(HttpStatus httpStatus, ErrorDto errorDto) {
        return ResponseEntity
                .status(httpStatus)
                .body(errorDto);
    }
}
