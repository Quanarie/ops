package com.ops.ops.utils.exceptions.ops.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends OpsException {

    public NotFoundException(String errorMessage, String exceptionCode) {
        super(errorMessage, exceptionCode, HttpStatus.NOT_FOUND);
    }
}