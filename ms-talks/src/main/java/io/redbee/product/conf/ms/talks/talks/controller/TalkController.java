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
    public void createTalk(@RequestBody Talk talk) {talkCreationService.save(talk);    }


}
