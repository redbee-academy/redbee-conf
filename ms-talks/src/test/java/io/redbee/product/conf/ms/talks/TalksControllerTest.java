package io.redbee.product.conf.ms.talks;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.product.conf.ms.talks.talks.controller.TalksController;
import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.service.TalkService;
import org.junit.jupiter.api.Test;
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
    TalkService talkCreationService = mock(TalkService.class);
    mockMvc = standaloneSetup(new TalksController(talkCreationService)).build();
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
