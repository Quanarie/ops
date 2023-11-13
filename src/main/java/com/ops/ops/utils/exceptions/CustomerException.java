package com.ops.ops.utils.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerException extends RuntimeException{

    private final String exceptionCode;

    private final HttpStatus httpStatus;

    public CustomerException(String errorMessage, String exceptionCode, HttpStatus httpStatus) {
        super(errorMessage);
        this.exceptionCode = exceptionCode;
        this.httpStatus = httpStatus;
    }
}
