package com.abhi.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException extends RuntimeException {

    public UserException(String exception, int code) {
        super(exception);
    }

}
