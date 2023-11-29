package com.ops.ops.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends OpsException {

    public NotFoundException(String errorMessage, ExceptionCodes exceptionCode) {
        super(errorMessage, exceptionCode, HttpStatus.NOT_FOUND);
    }
}
