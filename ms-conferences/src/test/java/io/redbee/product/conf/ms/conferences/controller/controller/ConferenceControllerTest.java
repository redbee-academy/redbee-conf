package io.redbee.product.conf.ms.conferences.controller.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateAlreadyExistsException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import liquibase.integration.spring.SpringLiquibase;
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

    @Test
    @DisplayName("When updating a conference with invalid startDate it should return 422")
    void testUpdateStartDateBeforeTodayShouldReturnUnprocessableEntity() throws Exception {
        final var request = ConferenceFactory.getConference();
        final var requestJson = objectMapper.writeValueAsString(request);

        Mockito.when(conferenceService.update(Mockito.any()))
                .thenThrow(new StartDateMustBeAfterTodayException());

        mockMvc.perform(
                put("/conference/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn();
    }

    @Test
    @DisplayName("When updating a conference with an existing startDate it should return 422")
    void testUpdateStartDateAlreadyExistsShouldReturnUnprocessableEntity() throws Exception {
        final var request = ConferenceFactory.getConference();
        final var requestJson = objectMapper.writeValueAsString(request);

        Mockito.when(conferenceService.update(Mockito.any()))
                .thenThrow(new StartDateAlreadyExistsException(request.getStartDate()));

        mockMvc.perform(
                put("/conference/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn();
    }

}
