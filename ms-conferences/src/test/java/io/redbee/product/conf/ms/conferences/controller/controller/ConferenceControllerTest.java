package io.redbee.product.conf.ms.conferences.controller.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateAlreadyExistsException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

    @MockBean
    private ConferenceService conferenceService;

    @MockBean
    private SpringLiquibase springLiquibase;

    @Test
    @DisplayName("When requested an visible conference it should return it")
    void testGetConferenceByStatusSuccessfully() throws Exception {
        final var expected = ConferenceFactory.getConference();
        final var expectedJson = objectMapper.writeValueAsString(expected);
        Mockito.when(conferenceService.getConfVisible()).thenReturn(expected);

        final var response =
                mockMvc.perform(get("/conference")).andReturn().getResponse();
    }

    @Test
    @DisplayName("When requested an existing conference it should return it")
    void testGetConferenceByIdSuccessfully() throws Exception {
        final var expected = ConferenceFactory.getConference();
        final var expectedJson = objectMapper.writeValueAsString(expected);
        Mockito.when(conferenceService.getById(Mockito.eq(expected.getId()))).thenReturn(expected);

        final var response =
                mockMvc.perform(get("/conference/" + expected.getId())).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("When updating an existing conference it should return the updated instance")
    void testUpdateConferenceSuccessfully() throws Exception {
        final var expected = ConferenceFactory.getConference();
        final var expectedJson = objectMapper.writeValueAsString(expected);
        Mockito.when(conferenceService.update(Mockito.any())).thenReturn(expected);

        final var response =
                mockMvc.perform(
                        put("/conference/" + expected.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(expectedJson)
                ).andReturn().getResponse();

        assertEquals(201, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("When updating a conference with invalid dates it should return 422")
    void testUpdateEndDateBeforeStartDateShouldReturnUnprocessableEntity() throws Exception {
        final var request = ConferenceFactory.getConference();
        final var requestJson = objectMapper.writeValueAsString(request);

        Mockito.when(conferenceService.update(Mockito.any()))
                .thenThrow(new EndDateMustBeAfterStartDateException(""));

        mockMvc.perform(
                put("/conference/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn();
    }

    @DisplayName("Given a conf that already exists, throw exception")
    void createConfAlreadyExists() throws Exception {
        final var request = ConferenceFactory.getConference();
        final var requestJson = objectMapper.writeValueAsString(request);
        Conference conf = ConferenceFactory.getConference();

        final MockHttpServletResponse response =
                this.mockMvc.perform(post("/conference")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(conf)))
                        .andReturn().getResponse();

        assertEquals(422, response.getStatus());
    }

//    @Test
//    @DisplayName("Given a conf that already exists in visibility false, is not created. Get status 422")
//    void createConfWithVisibilityFalse() throws Exception {
//        Conference conf = ConferenceFactory.getConference();
//        conf.setStatus(false);
//
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(conf)))
//                        .andReturn().getResponse();
//
//        assertEquals(422, response.getStatus());
//    }

//    @Test
//    @DisplayName("Given a conf that the start date is before today, get status 422")
//    void createConfStartDateBeforeTodayTest() throws Exception {
//        LocalDateTime startDate = LocalDateTime.parse("2021-09-25T22:17:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setStartDate(startDate);
//
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(conf)))
//                        .andReturn().getResponse();
//
//        assertEquals(422, response.getStatus());
//
//    }

//    @Test
//    @DisplayName("Given a conf that the end date is before start date, get status 422")
//    void createConfEndDateBeforeStartDate() throws Exception {
//        LocalDateTime endDate = LocalDateTime.parse("2021-09-25T22:17:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setEndDate(endDate);
//
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(conf)))
//                        .andReturn().getResponse();
//
//        assertEquals(422, response.getStatus());
//
//    }

    @Test
    @DisplayName("Given a conf that the end date is before start date, get status 422")
    void createConfEndDateBeforeStartDate1() throws Exception {
        final var request = ConferenceFactory.getConference();
        final var requestJson = objectMapper.writeValueAsString(request);
        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
        LocalDateTime endDate = LocalDateTime.parse("2021-11-25T21:17:52");
        Conference conf = ConferenceFactory.getConference();
        conf.setStartDate(startDate);
        conf.setEndDate(endDate);

        Mockito.when(conferenceService.update(Mockito.any()))
                .thenThrow(new StartDateMustBeAfterTodayException());

        mockMvc.perform(
                put("/conference/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn();
    }

//    @Test
//    @DisplayName("When updating a conference with an existing startDate it should return 422")
//    void testUpdateStartDateAlreadyExistsShouldReturnUnprocessableEntity() throws Exception {
//        final var request = ConferenceFactory.getConference();
//        final var requestJson = objectMapper.writeValueAsString(request);
//        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setStartDate(startDate);
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(conf)))
//                        .andReturn().getResponse();
//
//        Mockito.when(conferenceService.update(Mockito.any()))
//                .thenThrow(new StartDateAlreadyExistsException(request.getStartDate()));
//
//        mockMvc.perform(
//                put("/conference/" + request.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
//                .andReturn();
//        assertEquals(422, response.getStatus());
//
//    }
//    @Test
//    @DisplayName("Given a conf that the end date is before start date, get status 422")
//    void createConfEndDateBeforeStartDate2() throws Exception {
//        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
//        LocalDateTime endDate = LocalDateTime.parse("2021-11-25T22:17:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setStartDate(startDate);
//        conf.setEndDate(endDate);
//
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(conf)))
//                        .andReturn().getResponse();
//
//        assertEquals(422, response.getStatus());
//
//    }

    @Test
    @DisplayName("Excpected status 200 of get /conference")
    void getVolumeConf() throws Exception {

        this.mockMvc.perform(get("/conference"))
                .andDo(print())
                .andExpect(status().isOk());

    }

//    @Test
//    @DisplayName("Excpected int of conf volume")
//    void getVolumeConf2() throws Exception {
//
//        MvcResult result = this.mockMvc.perform(get("/conference"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//        String contentAsString = result.getResponse().getContentAsString();
//
//        assertEquals("5",contentAsString);
//
//    }

}
