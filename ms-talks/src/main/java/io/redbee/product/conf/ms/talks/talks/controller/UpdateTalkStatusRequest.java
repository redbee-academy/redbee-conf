package io.redbee.product.conf.ms.talks.talks.controller;

import io.redbee.product.conf.ms.talks.talks.model.TalkStatus;

public class UpdateTalkStatusRequest {
  private final TalkStatus status;

  public UpdateTalkStatusRequest(TalkStatus status) {
    this.status = status;
  }

  public TalkStatus getStatus() {
    return status;
  }
}
