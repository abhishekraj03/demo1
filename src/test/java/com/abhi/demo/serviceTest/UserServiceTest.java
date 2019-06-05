package com.abhi.demo.serviceTest;


import com.abhi.demo.entity.User;
import com.abhi.demo.repo.UserRepo;
import com.abhi.demo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Before
    public void init(){
        userRepo.deleteAll();
    }

    @Test
    public void saveUser() {
        User userOne = new User();
        userOne.setName("abhishek");
        userOne.setDob(new Date());
        userService.saveUser(userOne);

        List<User> userInDb = userRepo.findAll();
        assertEquals(1, userInDb.size());
    }

    @Test
    public void saveUser_BAD_REQUEST() {
        User userOne = new User();
        userOne.setName(null);
        userOne.setDob(new Date());
        userService.saveUser(userOne);

        List<User> userInDb = userRepo.findAll();
        assertNotEquals(1, 2);
    }

    @After
    public void destroy(){
        userRepo.deleteAll();
    }
}
