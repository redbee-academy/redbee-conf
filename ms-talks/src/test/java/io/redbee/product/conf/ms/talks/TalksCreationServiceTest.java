package io.redbee.product.conf.ms.talks;

import io.redbee.product.conf.ms.talks.talks.dao.TalkDao;
import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.service.TalkCreationService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;



public class TalksCreationServiceTest {

  TalkDao talkDao = Mockito.mock(TalkDao.class);
  TalkCreationService talkCreationService = new TalkCreationService(talkDao);


  @Test
  void whenTalkCreatedSuccess() {

    Talk talk = TalkFactory.getTalk();

    Mockito.when(talkDao.save(talk)).thenReturn(talk.getId());

    Talk res = talkCreationService.create(
      talk.getRedbee_employee(),
      talk.getReference(),
      talk.getTalk_name(),
      talk.getTalk_topic(),
      talk.getTalk_description(),
      talk.getSpeaker_name(),
      talk.getSpeaker_email()
    );


    assertEquals(talk.getRedbee_employee(), res.getRedbee_employee());
    assertEquals(talk.getReference(), res.getReference());
    assertEquals(talk.getTalk_name(), res.getTalk_name());
    assertEquals(talk.getTalk_topic(), res.getTalk_topic());
    assertEquals(talk.getTalk_description(), res.getTalk_description());
    assertEquals(talk.getCreation_date(), res.getCreation_date());
    assertEquals(talk.getSpeaker_name(), res.getSpeaker_name());
    assertEquals(talk.getSpeaker_email(), res.getSpeaker_email());
  }
}
