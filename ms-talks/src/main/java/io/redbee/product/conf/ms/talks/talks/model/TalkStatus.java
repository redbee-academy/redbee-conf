package io.redbee.product.conf.ms.talks.talks.model;

public enum TalkStatus {
  PendingApproval(0),
  Approved(1);

  private final int value;

  private TalkStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  private static final TalkStatus[] cached_status_map = TalkStatus.values();

  public static TalkStatus parse(int value) {
    return cached_status_map[value];
  }
}
