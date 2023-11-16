package com.ops.ops.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class OpsException extends RuntimeException{

    private final String exceptionCode;

    private final HttpStatus httpStatus;

    public OpsException(String errorMessage, String exceptionCode, HttpStatus httpStatus) {
        super(errorMessage);
        this.exceptionCode = exceptionCode;
        this.httpStatus = httpStatus;
    }
}
