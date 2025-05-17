package org.caloidoscope.vaxseen.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.caloidoscope.vaxseen.constant.Constants.ErrorMessages.*;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(INVALID_ARGUMENT + ex.getMessage());
    }

    // Handle AuthenticationException (Bad credentials)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ignoredEx) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(INVALID_USERNAME_OR_PASSWORD);
    }

    // Handle MethodArgumentNotValidException (e.g., validation failures)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder(VALIDATION_FAILED);
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField()).append(" ").append(fieldError.getDefaultMessage()).append("; ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    }

    // Handle DataIntegrityViolationException (e.g., unique constraint violations)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(UNEXPECTED_ERROR_OCCURRED + ex.getMessage());
    }
}
