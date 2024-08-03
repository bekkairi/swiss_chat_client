package com.swiss.sharing.client.service.api.v1;

import com.swiss.sharing.client.service.dto.ValidationError;
import com.swiss.sharing.client.service.exceptions.UserServiceException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ValidationError> handleConstraintViolation(ConstraintViolationException ex) {
        log.debug(
                "Constraint violation exception encountered: {}",
                ex.getConstraintViolations(),
                ex
        );
        return buildValidationErrors(ex.getConstraintViolations());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleConstraintViolation(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        log.debug(
                "Validation error",
                ex.getMessage(),
                ex
        );

        return new ResponseEntity(fieldErrors.stream().map(
                e -> ValidationError.builder().object(result.getTarget()).error(e.getDefaultMessage()).field(e.getField()).build()
        ).collect(toList()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ValidationError handleConstraintViolation(UserServiceException ex) {
        return ValidationError.builder()
                .field(ex.getClass().getSimpleName())
                .error(ex.getMessage()).build();
    }

    private List<ValidationError> buildValidationErrors(
            Set<ConstraintViolation<?>> violations) {
        return violations.
                stream().
                map(violation ->
                        ValidationError.builder().
                                field(
                                        StreamSupport.stream(
                                                        violation.getPropertyPath().spliterator(), false).
                                                reduce((first, second) -> second).
                                                orElse(null).
                                                toString()
                                ).
                                error(violation.getMessage()).
                                build()).
                collect(toList());
    }
}
