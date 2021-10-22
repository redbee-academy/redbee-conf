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


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ConferenceControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("Given a request to create conf when call get status 201")
//    void createConf() throws Exception {
//
//        Conference conf = ConferenceFactory.getConference();
//
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(conf)))
//                        .andReturn().getResponse();
//
//        assertEquals(201, response.getStatus());
//
//    }
//
//    @Test
//    @DisplayName("Given a conf that already exists, throw exception")
//    void createConfAlreadyExists() throws Exception {
//        Conference conf1 = ConferenceFactory.getConference();
//
//        final MockHttpServletResponse response =
//                this.mockMvc.perform(post("/conference")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(conf1)))
//                        .andReturn().getResponse();
//
//        assertEquals(422, response.getStatus());
//    }
//
//}
