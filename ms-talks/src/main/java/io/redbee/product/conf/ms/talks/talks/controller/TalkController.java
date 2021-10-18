package io.redbee.product.conf.ms.talks.talks.controller;

import io.redbee.product.conf.ms.talks.talks.mapper.TalkToResponseMapper;
import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.model.TalkCreationRequest;
import io.redbee.product.conf.ms.talks.talks.model.TalkResponse;
import io.redbee.product.conf.ms.talks.talks.service.TalkCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/talks")
public class TalkController {

    private final TalkCreationService talkCreationService;
    private final TalkToResponseMapper responseMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TalkController.class);

    public TalkController(TalkCreationService talkCreationService, TalkToResponseMapper responseMapper) {
        this.talkCreationService = talkCreationService;
        this.responseMapper = responseMapper;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public TalkResponse createTalk(@RequestBody @Valid TalkCreationRequest talk) {
        LOGGER.info("create: creating talk {}", talk.getTalk_name());
        TalkResponse response = mapResponse(
                talkCreationService.create(talk.getTalk_name(), talk.getTalk_topic())
        );
        LOGGER.info("create: talk created {}", response);
        return response;
    }


    private TalkResponse mapResponse(Talk talk)  {
        return responseMapper.map(talk);
    }
}
