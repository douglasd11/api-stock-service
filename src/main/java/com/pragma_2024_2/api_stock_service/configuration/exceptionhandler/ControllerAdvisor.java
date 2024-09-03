package com.pragma_2024_2.api_stock_service.configuration.exceptionhandler;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_2024_2.api_stock_service.configuration.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(
                        Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,
                        HttpStatus.NOT_FOUND.toString(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException() {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }


    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(
                        Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE,
                        HttpStatus.CONFLICT.toString(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        errors.toString(),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        errors.toString(),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
}