package com.abhi.demo.controllerTest;

import com.google.gson.Gson;
import com.t4p.dutchit.persistence.User;
import com.t4p.dutchit.repository.UserRepo;
import com.t4p.dutchit.web.beans.UserBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends BaseControllerTest {

    Gson gson = new Gson();
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init(){
        userRepo.deleteAll();
    }

    @Test
    public void saveAllUser()throws Exception{
        UserBean userBean =  new UserBean();
        userBean.setEmailId("raj@gmail.com");
        userBean.setName("abhi");

        String counterJson = gson.toJson(userBean);
        this.mockMvc.perform(post("/user").content(counterJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        List<User> usersInDb = userRepo.findAll();
        Assert.assertEquals(1, usersInDb.size());

        User oneUser = usersInDb.get(0);
        Assert.assertEquals("abhi", oneUser.getName());
        Assert.assertEquals("raj@gmail.com", oneUser.getEmailId());

    }

    @Test
    public void getAllUser()throws Exception{
        UserBean userBean =  new UserBean();
        userBean.setEmailId("raj@gmail.com");
        userBean.setName("abhi");


        String counterJson = gson.toJson(userBean);
        this.mockMvc.perform(post("/user").content(counterJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        List<User> usersInDb = userRepo.findAll();
        Assert.assertEquals(1, usersInDb.size());

        User oneUser = usersInDb.get(0);
        Assert.assertEquals("abhi", oneUser.getName());
        Assert.assertEquals("raj@gmail.com",oneUser.getEmailId());

        String url = "/user";

        this.mockMvc.perform(get(url).sessionAttr("userRepo", this.userRepo).param("name", userBean.getName())
                 ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUserById()throws Exception{
        UserBean userBean =  new UserBean();
        userBean.setEmailId("raj@gmail.com");
        userBean.setName("abhi");
        userBean.setId(3L);


        String counterJson = gson.toJson(userBean);
        this.mockMvc.perform(post("/user").content(counterJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        List<User> usersInDb = userRepo.findAll();
        Assert.assertEquals(1, usersInDb.size());

        User oneUser = usersInDb.get(0);
        Assert.assertEquals("abhi", oneUser.getName());
        Assert.assertEquals("raj@gmail.com", oneUser.getEmailId());

        String url = "/user/" + oneUser.getId();
        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
    }

    //localhost:8080/users/email?email=abhi@gmail.com
    @Test
    public void getUserByEmailId() throws Exception {
        UserBean userBean = new UserBean();
        userBean.setEmailId("raj@gmail.com");
        userBean.setName("abhi");
        userBean.setId(3L);


        String counterJson = gson.toJson(userBean);
        this.mockMvc.perform(post("/user").content(counterJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        List<User> usersInDb = userRepo.findAll();
        Assert.assertEquals(1, usersInDb.size());

        User oneUser = usersInDb.get(0);
        Assert.assertEquals("abhi", oneUser.getName());
        Assert.assertEquals("raj@gmail.com", oneUser.getEmailId());

        String url = "/user/email?email=" + oneUser.getEmailId();
        this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteUserById()throws Exception{
        UserBean userBean =  new UserBean();
        userBean.setEmailId("raj@gmail.com");
        userBean.setName("abhi");
        userBean.setId(4L);

        String counterJson = gson.toJson(userBean);
        this.mockMvc.perform(post("/user").content(counterJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        List<User> usersInDb = userRepo.findAll();
        Assert.assertEquals(1, usersInDb.size());

        User oneUser = usersInDb.get(0);
        Assert.assertEquals("abhi", oneUser.getName());
        Assert.assertEquals("raj@gmail.com",oneUser.getEmailId());

        String url = "/user/" + oneUser.getId();
        this.mockMvc.perform(delete(url)).andExpect(status().isOk());

        usersInDb = userRepo.findAll();
        Assert.assertEquals(0, usersInDb.size());

    }

    @After
    public void destroy(){
        userRepo.deleteAll();
    }
}
