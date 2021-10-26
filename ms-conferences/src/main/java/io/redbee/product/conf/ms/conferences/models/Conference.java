package io.redbee.product.conf.ms.conferences.models;

import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

// cp .idea .idea.bkp -r   , git merge master   , rm .idea -r   ,mv .idea.bkp .idea
public class Conference {
    private Integer id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private Boolean visibility;
    private Integer volume;

    public Conference(Integer id, String name, Integer volume, LocalDateTime startDate, LocalDateTime endDate, String description, Boolean visibility) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.visibility = visibility;
        this.volume=volume;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Conference() {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return visibility;
    }

    public void setStatus(Boolean visibility) {
        this.visibility = visibility;
    }

    public Conference copyId(Integer id) {
        return new ConferenceBuilder()
                .basedOn(this)
                .id(id)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference conference = (Conference) o;
        return Objects.equals(id, conference.id) && Objects.equals(name, conference.name) &&
                Objects.equals(startDate, conference.startDate) &&
                Objects.equals(endDate, conference.endDate) &&
                Objects.equals(visibility, conference.visibility);
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
