package io.redbee.product.conf.ms.talks.talks.controller;

import java.time.LocalDateTime;

public class PostulateTalkRequest {
    private Boolean redbee_employee;
    private String reference;
    private String talk_name;
    private String talk_topic;
    private String talk_description;
    private LocalDateTime creation_date;
    private String speaker_name;
    private String speaker_email;

    public PostulateTalkRequest(
            Boolean redbee_employee,
            String reference,
            String talk_name,
            String talk_topic,
            String talk_description,
            LocalDateTime creation_date,
            String speaker_name,
            String speaker_email
    ) {
        this.redbee_employee = redbee_employee;
        this.reference = reference;
        this.talk_name = talk_name;
        this.talk_topic = talk_topic;
        this.talk_description = talk_description;
        this.creation_date = creation_date;
        this.speaker_name = speaker_name;
        this.speaker_email = speaker_email;
    }

    public Boolean getRedbee_employee() { return redbee_employee; }
    public String getReference() { return reference; }
    public String getTalk_name() { return talk_name; }
    public String getTalk_topic() { return talk_topic; }
    public String getTalk_description() { return talk_description; }
    public LocalDateTime getCreation_date() { return creation_date; }
    public String getSpeaker_name() { return speaker_name; }
    public String getSpeaker_email() { return speaker_email; }
}
