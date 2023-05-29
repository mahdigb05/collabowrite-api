package com.z7.collabowriteapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNonFoundException extends Exception{

    public UserNonFoundException() {
        super();
    }
    public UserNonFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNonFoundException(String message) {
        super(message);
    }
    public UserNonFoundException(Throwable cause) {
        super(cause);
    }

}
