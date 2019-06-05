package com.abhi.demo.exception;

public class UserException extends RuntimeException {

    public UserException(String exception, int code) {
        super(exception);
    }

}
