package io.redbee.product.conf.ms.talks.talks.model;

import io.redbee.product.conf.ms.talks.talks.builder.TalkBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Talk implements Serializable {
    private final Integer id;
    private final Boolean redbee_employee;
    private final String reference;
    private final String talk_name;
    private final String talk_topic;
    private final String talk_description;
    private final LocalDateTime creation_date;

    public Talk(Integer id, Boolean redbee_employee, String reference, String talk_name, String talk_topic, String talk_description, LocalDateTime creation_date) {
        this.id = id;
        this.redbee_employee = redbee_employee;
        this.reference = reference;
        this.talk_name = talk_name;
        this.talk_topic = talk_topic;
        this.talk_description = talk_description;
        this.creation_date = creation_date;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getRedbee_employee() {
        return redbee_employee;
    }

    public String getReference() {
        return reference;
    }

    public String getTalk_name() {
        return talk_name;
    }

    public String getTalk_topic() {
        return talk_topic;
    }

    public String getTalk_description() {
        return talk_description;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public Talk copyId(Integer id) {
        return new TalkBuilder()
                .basedOn(this)
                .id(id)
                .build();
    }


}
