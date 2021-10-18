package io.redbee.product.conf.ms.talks.talks.model;

public class TalkCreationRequest {
    private String talk_name;
    private String talk_topic;

    public TalkCreationRequest() {}

    public String getTalk_name() {
        return talk_name;
    }

    public void setTalk_name(String talk_name) {
        this.talk_name = talk_name;
    }

    public String getTalk_topic() {
        return talk_topic;
    }

    public void setTalk_topic(String talk_topic) {
        this.talk_topic = talk_topic;
    }
}
