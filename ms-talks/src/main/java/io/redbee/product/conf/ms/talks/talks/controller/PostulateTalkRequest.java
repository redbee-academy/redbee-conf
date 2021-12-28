package io.redbee.product.conf.ms.talks.talks.controller;

import java.time.LocalDateTime;

public class PostulateTalkRequest {
    private final Boolean redbee_employee;
    private final String reference;
    private final String talk_name;
    private final String talk_topic;
    private final String talk_description;
    private final String speaker_name;
    private final String speaker_email;
    private final Integer conference_id;

    public PostulateTalkRequest(
            Boolean redbee_employee,
            String reference,
            String talk_name,
            String talk_topic,
            String talk_description,
            LocalDateTime creation_date,
            String speaker_name,
            String speaker_email,
            Integer conference_id
    ) {
        this.redbee_employee = redbee_employee;
        this.reference = reference;
        this.talk_name = talk_name;
        this.talk_topic = talk_topic;
        this.talk_description = talk_description;
        this.speaker_name = speaker_name;
        this.speaker_email = speaker_email;
        this.conference_id = conference_id;
    }

    public Boolean getRedbee_employee() { return redbee_employee; }
    public String getReference() { return reference; }
    public String getTalk_name() { return talk_name; }
    public String getTalk_topic() { return talk_topic; }
    public String getTalk_description() { return talk_description; }
    public String getSpeaker_name() { return speaker_name; }
    public String getSpeaker_email() { return speaker_email; }
    public Integer getConference_id() { return conference_id; }
}
