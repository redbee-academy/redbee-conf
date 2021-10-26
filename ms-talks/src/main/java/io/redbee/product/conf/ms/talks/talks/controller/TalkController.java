package io.redbee.product.conf.ms.talks.talks.controller;

import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.service.TalkCreationService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/talks")
public class TalkController {

    private final TalkCreationService talkCreationService;

    public TalkController(TalkCreationService talkCreationService) {
        this.talkCreationService = talkCreationService;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public void createTalk(@RequestBody Talk talk) {
        talkCreationService.create(
                talk.getRedbee_employee(),
                talk.getReference(),
                talk.getTalk_name(),
                talk.getTalk_topic(),
                talk.getTalk_description(),
                talk.getSpeaker_name(),
                talk.getSpeaker_email()
        );
    }


}
