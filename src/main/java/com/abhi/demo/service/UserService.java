package com.abhi.demo.service;

import com.abhi.demo.entity.User;
import com.abhi.demo.repo.UserRepo;
import com.abhi.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    @Transactional
    public User saveUser(User user) {
        UserValidator.isValidUserRequest(user);
        return userRepo.save(user);
    }

    @Transactional
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

}
