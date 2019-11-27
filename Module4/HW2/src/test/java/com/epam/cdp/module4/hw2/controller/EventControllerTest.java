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
public class EventControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private static final String BAND_1 = "Rammstein European Tour. Paris";
    private static final String BAND_2 = "Metallica Stadion Grand Tour. Poznan";
    private static final String BAND_3 = "Scorpions Germany Tour. Berlin";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getEventById() throws Exception {
        this.mockMvc.perform(get("/events/12"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(containsString(BAND_1)));
    }

    @Test
    public void getEventsByTitle() throws Exception {
        this.mockMvc.perform(get("/events?eventTitle=Tour&pageSize=3&page=0"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(containsString(BAND_1)))
                .andExpect(content().string(containsString(BAND_2)))
                .andExpect(content().string(containsString(BAND_3)));
    }

    @Test
    public void getEventsByTitleNextPage() throws Exception {
        this.mockMvc.perform(get("/events?eventTitle=Tour&pageSize=2&page=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(not(containsString(BAND_1))))
                .andExpect(content().string(not(containsString(BAND_2))))
                .andExpect(content().string(containsString(BAND_3)));
    }

    @Test
    public void getEventsByDate() throws Exception {
        this.mockMvc.perform(get("/events?eventDate=2019-12-07&pageSize=3&page=0"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(not(containsString(BAND_1))))
                .andExpect(content().string(containsString(BAND_2)))
                .andExpect(content().string(not(containsString(BAND_3))));
    }

    @Test
    public void createEvent() throws Exception {
        this.mockMvc.perform(get("/events/create?title='Motanka Ukraine Tour.'&date=2019-12-07"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(containsString("Motanka Ukraine Tour.")));

    }

    @Test
    public void updateEvent() throws Exception {
        this.mockMvc.perform(get("/events/update?id=16&title=Killswitch Engage Sweden Tour&date=2019-12-08"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(containsString("16")))
                .andExpect(content().string(containsString("Killswitch Engage Sweden Tour")))
                .andExpect(content().string(containsString("2019-12-08")));
    }

    @Test
    public void deleteEvent() throws Exception {
        this.mockMvc.perform(get("/events/delete?id=15"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(content().string(not(containsString("Jinjer Austria Tour. Vienna"))))
                .andExpect(content().string(containsString("true")));
    }
}