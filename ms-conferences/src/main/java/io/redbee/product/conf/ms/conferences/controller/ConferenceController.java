package io.redbee.product.conf.ms.conferences.controller;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void createConference(@RequestBody Conference conference) {
        conferenceService.create(conference);
    }

    @GetMapping("/volume")
    public Integer getNextVolume(){
       return conferenceService.getConferenceVolume();
    }


    // ver si puede ser útil devolver una Conference o con un CREATED alcanza

}








