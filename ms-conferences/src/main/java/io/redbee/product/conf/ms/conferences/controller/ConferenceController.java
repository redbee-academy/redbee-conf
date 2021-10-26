package io.redbee.product.conf.ms.conferences.controller;
import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RestController
@RequestMapping("/conference")
@CrossOrigin("*")
public class ConferenceController {
    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConference(@RequestBody ConferenceRest conferenceRest) {
        System.out.println(conferenceRest);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        conferenceService.create(new ConferenceBuilder()
                        .name("redbee conf vol.")
                .startDate(LocalDate.parse(conferenceRest.getStartDate(), formatter).atStartOfDay())
                .endDate(LocalDate.parse(conferenceRest.getEndDate(), formatter).atStartOfDay())
                        .description(conferenceRest.getDescription())
                        .visibility(conferenceRest.getStatus())
                .build());
    }

    @GetMapping("/volume")
    public Integer getNextVolume(){
       return conferenceService.getConferenceVolume();
    }


    // ver si puede ser útil devolver una Conference o con un CREATED alcanza

}








