package io.redbee.product.conf.ms.conferences.controller;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceCreationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/conference")
public class ConferenceController {
    private final ConferenceCreationService conferenceService;

    public ConferenceController(ConferenceCreationService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConference(@RequestBody Conference conference) {  // recibo a travès de requestBody
        conferenceService.create(conference.getName(),conference.getStartDate(),
                conference.getEndDate(),conference.getDescription());
    }

    // ver si puede ser útil devolver una Conference o con un CREATED alcanza

}








