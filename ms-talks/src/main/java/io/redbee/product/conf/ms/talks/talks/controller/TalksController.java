package io.redbee.product.conf.ms.talks.talks.controller;

import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.model.TalkStatus;
import io.redbee.product.conf.ms.talks.talks.service.TalkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/talks")
public class TalksController {

    private final TalkService talkService;

    public TalksController(TalkService talkCreationService) {
        this.talkService = talkCreationService;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public void createTalk(@RequestBody PostulateTalkRequest talk) {
        talkService.create(
          talk.getRedbee_employee(),
          talk.getReference(),
          talk.getTalk_name(),
          talk.getTalk_topic(),
          talk.getTalk_description(),
          talk.getSpeaker_name(),
          talk.getSpeaker_email(),
          TalkStatus.PendingApproval,
          talk.getConference_id()
        );
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(OK)
    public void updateTalkStatus(@PathVariable Integer id, @RequestBody UpdateTalkStatusRequest updateTalkStatusRequest) {
        talkService.updateStatus(id, updateTalkStatusRequest.getStatus());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteTalk(@PathVariable Integer id) {
        talkService.delete(id);
    }
}
