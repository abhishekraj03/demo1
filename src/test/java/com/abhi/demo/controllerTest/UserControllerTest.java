package com.abhi.demo.controllerTest;

import com.abhi.demo.entity.User;
import com.abhi.demo.repo.UserRepo;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Gson gson;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveUserTest() throws Exception {
        User user = new User();
        user.setName("abhishek");

        String saveUserRequest = gson.toJson(user);
        this.mockMvc.perform(post("/user").content(saveUserRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void saveUserTest_BAD_REQUEST() throws Exception {
        User user = new User();

        String saveUserRequest = gson.toJson(user);
        this.mockMvc.perform(post("/user").content(saveUserRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Before
    public void init() {
        userRepo.deleteAll();
    }


    @After
    public void destroy() {
        userRepo.deleteAll();
    }


}
