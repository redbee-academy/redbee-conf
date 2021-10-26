package io.redbee.product.conf.ms.talks;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.product.conf.ms.talks.talks.controller.StatusController;
import io.redbee.product.conf.ms.talks.talks.controller.TalkController;
import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.service.TalkCreationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class TalksControllerTest {

  private MockMvc mockMvc;

  {
    TalkCreationService talkCreationService = mock(TalkCreationService.class);
    mockMvc = standaloneSetup(new TalkController(talkCreationService)).build();
  }

  private ObjectMapper objectMapper = new ObjectMapper();


  @Test
  void whenTalkCreated() throws Exception {

    Talk talk = TalkFactory.getTalk();


    final MockHttpServletResponse res =
      this.mockMvc.perform(post("/talks")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(talk)))
        .andReturn().getResponse();

    assertEquals(201, res.getStatus());
  }

}
