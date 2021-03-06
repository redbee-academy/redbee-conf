package io.redbee.product.conf.ms.conferences.builder;

import io.redbee.product.conf.ms.conferences.models.Conference;

import java.time.LocalDateTime;

public class ConferenceBuilder {
    private Integer id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private Boolean visibility;
    private Integer volume;

    public ConferenceBuilder id(Integer id) {
        this.id = id;
        return this;
    }
    public ConferenceBuilder volume(Integer volume){
        this.volume=volume;
        return this;
    }

    public ConferenceBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ConferenceBuilder startDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public ConferenceBuilder endDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public ConferenceBuilder description(String description) {
        this.description = description;
        return this;
    }



    public ConferenceBuilder visibility(Boolean visibility) {
        this.visibility = visibility;
        return this;
    }

    public ConferenceBuilder basedOn(Conference conference) {
        this.id = conference.getId();
        this.name = conference.getName();
        this.volume =conference.getVolume();
        this.startDate = conference.getStartDate();
        this.endDate = conference.getEndDate();
        this.description = conference.getDescription();
        this.visibility = conference.getStatus();

        return this;
    }

    public Conference build() {
        return new Conference(
                id,
                name,
                volume,
                startDate,
                endDate,
                description,
                visibility
        );
    }
}


