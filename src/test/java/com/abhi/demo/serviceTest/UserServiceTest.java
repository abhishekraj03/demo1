package com.abhi.demo.serviceTest;

import com.abhi.demo.entity.User;
import com.abhi.demo.repo.UserRepo;
import com.abhi.demo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Before
    public void init() {
        userRepo.deleteAll();
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setName("Abhishek");
        user.setDob("08/07/1992");
        user = userService.saveUser(user);
        assertEquals(1, userRepo.findAll().size());
        assertEquals("Abhishek", userRepo.findById(user.getId()).get().getName());
    }

    @Test
    public void saveUser_BAD_REQUEST() {
        User userOne = new User();
        userOne.setName(null);
        userOne.setDob("08/07/1992");
        userService.saveUser(userOne);

        List<User> userInDb = userRepo.findAll();
        assertNotEquals(1, 0);

    }

    @Test
    public void getAllUser() {
        User userOne = new User();
        userOne.setName("Abhishek");
        userService.saveUser(userOne);
        assertEquals(1, userRepo.findAll().size());

        User userTwo = new User();
        userTwo.setName("Raj");
        userService.saveUser(userTwo);
        assertEquals(2, userRepo.findAll().size());
    }


    @After
    public void destroy() {
        userRepo.deleteAll();
    }
}
