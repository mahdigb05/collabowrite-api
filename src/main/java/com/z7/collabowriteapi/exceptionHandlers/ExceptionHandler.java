package com.z7.collabowriteapi.exceptionHandlers;


import com.z7.collabowriteapi.entity.ErrorResponse;
import com.z7.collabowriteapi.exception.*;
import com.z7.collabowriteapi.exception.security.AuthenticationProviderException;
import com.z7.collabowriteapi.exception.security.InvalidAccessTokenException;
import com.z7.collabowriteapi.exception.security.MissingAccessTokenException;
import com.z7.collabowriteapi.exception.security.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundExceptionHandler(UserNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    // security exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingAccessTokenException.class)
    public ResponseEntity<Object> missingAccessTokenExceptionHandler(MissingAccessTokenException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAccessTokenException.class)
    public ResponseEntity<Object> invalidAccessTokenExceptionHandler(InvalidAccessTokenException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> internalServerExceptionHandler(InternalServerError exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationProviderException.class)
    public ResponseEntity<Object> authenticationProviderExceptionHandler(AuthenticationProviderException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }




}
