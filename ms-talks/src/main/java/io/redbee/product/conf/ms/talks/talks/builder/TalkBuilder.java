package io.redbee.product.conf.ms.talks.talks.builder;

import io.redbee.product.conf.ms.talks.talks.model.Talk;

import java.time.LocalDateTime;

public class TalkBuilder {
  private Integer id;
  private Boolean redbee_employee;
  private String reference;
  private String talk_name;
  private String talk_topic;
  private String talk_description;
  private LocalDateTime creation_date;
  private String speaker_name;
  private String speaker_email;

  public TalkBuilder id(Integer id) {
    this.id = id;
    return this;
  }

  public TalkBuilder redbee_employee(Boolean redbee_employee) {
    this.redbee_employee = redbee_employee;
    return this;
  }

  public TalkBuilder reference(String reference) {
    this.reference = reference;
    return this;
  }

  public TalkBuilder talk_name(String talk_name) {
    this.talk_name = talk_name;
    return this;
  }

  public TalkBuilder talk_topic(String talk_topic) {
    this.talk_topic = talk_topic;
    return this;
  }

  public TalkBuilder talk_description(String talk_description) {
    this.talk_description = talk_description;
    return this;
  }

  public TalkBuilder creation_date(LocalDateTime creation_date) {
    this.creation_date = creation_date;
    return this;
  }

  public TalkBuilder speaker_name(String speaker_name) {
    this.speaker_name = speaker_name;
    return this;
  }

  public TalkBuilder speaker_email(String speaker_email) {
    this.speaker_email = speaker_email;
    return this;
  }

  public TalkBuilder basedOn(Talk talk) {
    this.id = talk.getId();
    this.redbee_employee = talk.getRedbee_employee();
    this.reference = talk.getReference();
    this.talk_name = talk.getTalk_name();
    this.talk_topic = talk.getTalk_topic();
    this.talk_description = talk.getTalk_description();
    this.creation_date = talk.getCreation_date();
    this.speaker_name = talk.getSpeaker_name();
    this.speaker_email = talk.getSpeaker_email();

    return this;
  }

  public Talk build() {
    return new Talk(
      id,
      redbee_employee,
      reference,
      talk_name,
      talk_topic,
      talk_description,
      creation_date,
      speaker_name,
      speaker_email
    );
  }
}
