package io.redbee.product.conf.ms.talks.talks.mapper;

import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.model.TalkResponse;
import org.springframework.stereotype.Component;

@Component
public class TalkToResponseMapper {

    public TalkResponse map(Talk talk) {
        return new TalkResponse(
                talk.getTalk_name(),
                talk.getTalk_topic()
        );
    }
}
