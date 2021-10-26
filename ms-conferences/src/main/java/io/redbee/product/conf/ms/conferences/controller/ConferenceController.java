package io.redbee.product.conf.ms.conferences.controller;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConference(@RequestBody Conference conference) {  // recibo a travès de requestBody
        conferenceService.create(conference.getName(),conference.getStartDate(),
                conference.getEndDate(),conference.getDescription(),conference.getStatus());
    }

    // ver si puede ser útil devolver una Conference o con un CREATED alcanza
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public Conference getConferenceByVisibility(){
        return conferenceService.getConfVisible();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Conference getConferenceById(@PathVariable Integer id){
        return conferenceService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Conference updateConference(@RequestBody Conference conference, @PathVariable Integer id) {
      return conferenceService.update(conference.copyId(id));
    }

}








