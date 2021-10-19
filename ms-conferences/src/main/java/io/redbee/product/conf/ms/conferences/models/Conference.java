package io.redbee.product.conf.ms.conferences.models;

import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

// cp .idea .idea.bkp -r   , git merge master   , rm .idea -r   ,mv .idea.bkp .idea
public class Conference {
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Status visibility;

    public Conference() {
    }

    public Conference(Integer id, String name, LocalDate startDate, LocalDate endDate, String description, Status visibility) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.visibility = visibility;
    }

    public Conference(int id, String name, LocalDateTime start_date, LocalDateTime end_date, String description) {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getVisibility() {
        return visibility;
    }

    public void setVisibility(Status visibility) {
        this.visibility = visibility;
    }

    public Conference copyId(Integer id) {
        return new ConferenceBuilder()
                .basedOn(this)
                .id(id)
                .build();
    }
    @Override
    public String toString() {
        return "Conference{" +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", visibility=" + visibility +
                '}';
    }
}
