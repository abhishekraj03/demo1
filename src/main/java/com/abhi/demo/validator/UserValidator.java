package com.abhi.demo.validator;

import com.abhi.demo.entity.User;
import com.abhi.demo.exception.UserException;
import org.springframework.http.HttpStatus;

public class UserValidator {

    public static Boolean isValidUserRequest(User user) {
        if (user.getName() == null)
            throw new UserException("User name can not be null", HttpStatus.BAD_REQUEST.value());

        if (user.getDob() == null)
            throw new UserException("DOB can not be null", HttpStatus.BAD_REQUEST.value());
        return true;
    }
}
