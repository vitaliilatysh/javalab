package com.epam.cdp.module4.hw2.controller;

import com.epam.cdp.module4.hw2.config.AppConfig;
import com.epam.cdp.module4.hw2.config.WebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class, AppConfig.class})
public class UserControllerTest {

    private static final String EMAIL_1 = "andrii_manzhos@gmail.com";
    private static final String EMAIL_2 = "pylyp_dankovets@gmail.com";
    private static final String EMAIL_3 = "andrii_panko@gmail.com";
    private static final String EMAIL_4 = "andrii_rehtlii@gmail.com";
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getUserById() throws Exception {
        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(containsString("Andrii")))
                .andExpect(content().string(containsString(EMAIL_1)));
    }

    @Test
    public void getUserByIdNotFound() throws Exception {
        this.mockMvc.perform(get("/users/22"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(content().string(containsString("User not found with id=22")));
    }

    @Test
    public void getUsersByName() throws Exception {
        this.mockMvc.perform(get("/users?userName=Andrii&pageSize=3&page=0"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(containsString(EMAIL_1)))
                .andExpect(content().string(containsString(EMAIL_3)))
                .andExpect(content().string(containsString(EMAIL_4)))
                .andExpect(content().string(not(containsString(EMAIL_2))));
    }

    @Test
    public void getUsersByEmail() throws Exception {
        this.mockMvc.perform(get("/users?email=pylyp_dankovets@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(not(containsString(EMAIL_1))))
                .andExpect(content().string(not(containsString(EMAIL_3))))
                .andExpect(content().string(not(containsString(EMAIL_4))))
                .andExpect(content().string(containsString(EMAIL_2)));
    }

    @Test
    public void createUser() throws Exception {
        this.mockMvc.perform(get("/users/create?name=Pavlo&email=pavlo_matiykhin@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(containsString("pavlo_matiykhin@gmail.com")));

    }

    @Test
    public void updateUser() throws Exception {
        this.mockMvc.perform(get("/users/update?id=45&name=Danylo&email=danylo_tereschyk@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(containsString("45")))
                .andExpect(content().string(containsString("Danylo")))
                .andExpect(content().string(containsString("danylo_tereschyk@gmail.com")));
    }

    @Test
    public void deleteUser() throws Exception {
        this.mockMvc.perform(get("/users/delete?id=46"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(not(containsString("vlad_koropiy@gmail.com"))))
                .andExpect(content().string(containsString("true")));
    }
}