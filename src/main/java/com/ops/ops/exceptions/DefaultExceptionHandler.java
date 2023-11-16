package com.ops.ops.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(OpsException.class)
    public ResponseEntity<ErrorDto> handleCustomerException(OpsException ex, WebRequest request) {
        ErrorDto errorDTO = new ErrorDto(ex.getLocalizedMessage(), ex.getExceptionCode());
        return buildResponseEntity(ex.getHttpStatus(), errorDTO);
    }

    private ResponseEntity<ErrorDto> buildResponseEntity(HttpStatus httpStatus, ErrorDto errorDto) {
        return ResponseEntity
                .status(httpStatus)
                .body(errorDto);
    }
}
