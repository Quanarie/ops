package com.ops.ops.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class OpsException extends RuntimeException {

    private final ExceptionCodes exceptionCode;

    private final HttpStatus httpStatus;

    public OpsException(String errorMessage, ExceptionCodes exceptionCode, HttpStatus httpStatus) {
        super(errorMessage);
        this.exceptionCode = exceptionCode;
        this.httpStatus = httpStatus;
    }
}
