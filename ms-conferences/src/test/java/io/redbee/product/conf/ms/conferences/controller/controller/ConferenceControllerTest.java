package io.redbee.product.conf.ms.conferences.controller.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ConferenceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given a request to create conf when call get status 201")
    void createConfStatus() throws Exception {

        Conference conf = ConferenceFactory.getConference();

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(201, response.getStatus());

    }

    @Test
    @DisplayName("Given a conf that already exists, throw exception")
    void createConfAlreadyExists() throws Exception {
        Conference conf = ConferenceFactory.getConference();

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());
    }

    @Test
    @DisplayName("Given a conf that already exists in visibility false, is not created. Get status 422")
    void createConfWithVisibilityFalse() throws Exception {
        Conference conf = ConferenceFactory.getConference();
        conf.setStatus(false);

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());
    }

    @Test
    @DisplayName("Given a conf that the start date is before today, get status 422")
    void createConfStartDateBeforeTodayTest() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2021-09-25T22:17:52");
        Conference conf = ConferenceFactory.getConference();
        conf.setStartDate(startDate);

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());

    }

    @Test
    @DisplayName("Given a conf that the end date is before start date, get status 422")
    void createConfEndDateBeforeStartDate() throws Exception {
        LocalDateTime endDate = LocalDateTime.parse("2021-09-25T22:17:52");
        Conference conf = ConferenceFactory.getConference();
        conf.setEndDate(endDate);

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());

    }

    @Test
    @DisplayName("Given a conf that the end date is before start date, get status 422")
    void createConfEndDateBeforeStartDate1() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
        LocalDateTime endDate = LocalDateTime.parse("2021-11-25T21:17:52");
        Conference conf = ConferenceFactory.getConference();
        conf.setStartDate(startDate);
        conf.setEndDate(endDate);

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());

    }
    @Test
    @DisplayName("Given a conf that the end date is before start date, get status 422")
    void createConfEndDateBeforeStartDate2() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
        LocalDateTime endDate = LocalDateTime.parse("2021-11-25T22:17:52");
        Conference conf = ConferenceFactory.getConference();
        conf.setStartDate(startDate);
        conf.setEndDate(endDate);

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());

    }

    @Test
    @DisplayName("Excpected status 200 of get /conference")
    void getVolumeConf() throws Exception {

        this.mockMvc.perform(get("/conference"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Excpected int of conf volume")
    void getVolumeConf2() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/conference"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("5",contentAsString);

    }

}
