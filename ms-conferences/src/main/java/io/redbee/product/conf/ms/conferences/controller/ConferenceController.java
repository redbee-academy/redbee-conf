package io.redbee.product.conf.ms.conferences.controller;

import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
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

    @GetMapping()
    public List<Conference> getConferences(
      @RequestParam(required = false) Boolean current,
      @RequestParam(required = false) Boolean visible
    ) {
        if (current) {
            Optional<Conference> conf = conferenceService.getCurrentConf(visible);
            return conf.map(List::of).orElse(Collections.emptyList());
        }
        return conferenceService.getConferences();
    }

    @GetMapping("/volume")
    public Integer getNextVolume() {
        return conferenceService.getConferenceVolume();
    }

    @GetMapping("/current")
    public ResponseEntity<Conference> getCurrentConference(@RequestParam(required = false) Boolean visible) {
        return conferenceService
                .getCurrentConf(visible)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Conference getConferenceById(@PathVariable Integer id) {
        return conferenceService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Conference updateConference(@RequestBody Conference conference, @PathVariable Integer id) {
        return conferenceService.update(conference.copyId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConference(@PathVariable Integer id) {
        conferenceService.delete(id);
    }
}








