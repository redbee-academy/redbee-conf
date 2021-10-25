package io.redbee.product.conf.ms.talks;

import io.redbee.product.conf.ms.talks.TalkFactory;
import io.redbee.product.conf.ms.talks.talks.model.Talk;

import java.time.LocalDateTime;



public class TalkFactory {
  public static Talk getTalk() {
    LocalDateTime creationDate = LocalDateTime.parse("2021-11-27T22:17:52");
    return new Talk(
      2,
      true,
      "linkedin",
      "talk1",
      "microservices",
      "asdasdasd",
      creationDate);
  }
}
