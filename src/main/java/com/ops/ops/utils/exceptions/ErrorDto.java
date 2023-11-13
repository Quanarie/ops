package com.ops.ops.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public
class ErrorDto {

    private final String errorMessage;

    private final String exceptionCode;

}
