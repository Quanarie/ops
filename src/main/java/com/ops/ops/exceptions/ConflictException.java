package com.ops.ops.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends OpsException {

    public ConflictException(String errorMessage, ExceptionCodes exceptionCode) {
        super(errorMessage, exceptionCode, HttpStatus.CONFLICT);
    }
}
