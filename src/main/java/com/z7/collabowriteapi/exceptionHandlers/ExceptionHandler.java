package com.z7.collabowriteapi.exceptionHandlers;


import com.z7.collabowriteapi.entity.ErrorResponse;
import com.z7.collabowriteapi.exception.UserNonFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<Object> userNotFoundExceptionHandler(UserNonFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
