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

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class, AppConfig.class})
public class TicketControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getTicketsByEventOrderedByUserEmailAsc() throws Exception {
        this.mockMvc.perform(get("/tickets?eventId=13&pageSize=1&page=0"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(content().string(containsString("113")))
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("13")))
                .andExpect(content().string(containsString("456")))
                .andExpect(content().string(containsString("PREMIUM")));

        this.mockMvc.perform(get("/tickets?eventId=13&pageSize=1&page=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("2")))
                .andExpect(content().string(containsString("13")))
                .andExpect(content().string(containsString("333")))
                .andExpect(content().string(containsString("PREMIUM")));
    }

    @Test
    public void getTicketsByUserShouldBeOrderedByEventDateDesc() throws Exception {
        this.mockMvc.perform(get("/tickets?userId=1&pageSize=1&page=0"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(content().string(containsString("111")))
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("12")))
                .andExpect(content().string(containsString("455")))
                .andExpect(content().string(containsString("STANDARD")));

        this.mockMvc.perform(get("/tickets?userId=1&pageSize=1&page=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(content().string(containsString("113")))
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("13")))
                .andExpect(content().string(containsString("456")))
                .andExpect(content().string(containsString("PREMIUM")));
    }

    @Test
    public void bookTicket() throws Exception {
        this.mockMvc.perform(get("/tickets/book?eventId=16&userId=1&place=555&category=PREMIUM"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(content().string(containsString("16")))
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("555")))
                .andExpect(content().string(containsString("PREMIUM")));

    }

    @Test
    public void cancelTicket() throws Exception {
        this.mockMvc.perform(get("/tickets/cancel?id=115"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(content().string(containsString("true")));
    }

    @Test
    public void cancelTicketNotFoundById() throws Exception {
        this.mockMvc.perform(get("/tickets/cancel?id=22"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(content().string(containsString("Ticket not found with id=22")));
    }
}