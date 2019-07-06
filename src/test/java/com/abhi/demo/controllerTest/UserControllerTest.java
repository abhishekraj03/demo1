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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {
    Gson gson = new Gson();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @Before
    public void init() {
        userRepo.deleteAll();
    }

    @Test
    public void saveUserTest() throws Exception {
        User user = new User();
        user.setName("abhishek raj");
        String saveUserRequest = gson.toJson(user);
        this.mockMvc.perform(post("/user").content(saveUserRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("abhishek raj"))
                .andReturn();

    }

    @Test
    public void saveUserTest_BAD_REQUEST() throws Exception {
        String saveUserRequest = gson.toJson(null);
        this.mockMvc.perform(post("/user").content(saveUserRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void getAllUser() throws Exception {
        User userOne = new User();
        userOne.setName("abhishek");

        String saveUserRequest = gson.toJson(userOne);
        this.mockMvc.perform(post("/user").content(saveUserRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("abhishek"))
                .andReturn();

        User userTwo = new User();
        userTwo.setName("raj");

        saveUserRequest = gson.toJson(userTwo);
        this.mockMvc.perform(post("/user").content(saveUserRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("raj"))
                .andReturn();

        this.mockMvc.perform(get("/user")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @After
    public void destroy() {
        userRepo.deleteAll();
    }

}
