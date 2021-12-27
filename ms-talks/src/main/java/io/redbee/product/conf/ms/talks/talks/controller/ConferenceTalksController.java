package io.redbee.product.conf.ms.talks.talks.controller;

import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.model.TalkStatus;
import io.redbee.product.conf.ms.talks.talks.service.TalkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/conference/{id}/talks")
public class ConferenceTalksController {

  private final TalkService talkService;

  public ConferenceTalksController(TalkService talkCreationService) {
    this.talkService = talkCreationService;
  }

  @GetMapping()
  @ResponseStatus(OK)
  public List<Talk> getTalks(@PathVariable Integer id, @RequestParam(required = false) Boolean approved) {
    return talkService.getByConferenceId(id, approved == null ? null : approved ? TalkStatus.Approved : TalkStatus.PendingApproval);
  }
}
