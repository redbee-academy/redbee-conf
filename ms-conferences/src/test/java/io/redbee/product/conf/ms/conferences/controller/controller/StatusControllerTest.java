package io.redbee.product.conf.ms.conferences.controller.controller;

import io.redbee.product.conf.ms.conferences.controller.StatusController;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * @author Joaco Campero
 * <p>
 * created at 14/10/21
 */
class StatusControllerTest {

    @Test
    void probarController() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new StatusController()).build();

        final MockHttpServletResponse response = mockMvc.perform(get("/status")).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("ok", response.getContentAsString());


    }
}