package io.redbee.product.conf.ms.talks.talks.model;

import java.io.Serializable;

public class TalkResponse implements Serializable {

    private final String talk_name;
    private final String talk_topic;

    public TalkResponse(String talk_name, String talk_topic) {
        this.talk_name = talk_name;
        this.talk_topic = talk_topic;
    }

    public String getTalk_name() {
        return talk_name;
    }

    public String getTalk_topic() {
        return talk_topic;
    }
}
