package com.ops.exceptions.validation;

import com.ops.exceptions.ErrorDto;
import com.ops.exceptions.ExceptionCodes;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorDto> exceptionHandler(MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(),
                ExceptionCodes.VALIDATION_FAILED);

        return buildResponseEntity(errorDto);
    }

    @ExceptionHandler
    ResponseEntity<ErrorDto> exceptionHandler(ValidationException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(),
                ExceptionCodes.VALIDATION_FAILED);

        return buildResponseEntity(errorDto);
    }

    private ResponseEntity<ErrorDto> buildResponseEntity(ErrorDto errorDto) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }
}
