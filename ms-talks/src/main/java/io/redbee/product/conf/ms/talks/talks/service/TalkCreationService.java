package io.redbee.product.conf.ms.talks.talks.service;

import io.redbee.product.conf.ms.talks.talks.builder.TalkBuilder;
import io.redbee.product.conf.ms.talks.talks.dao.TalkDao;
import io.redbee.product.conf.ms.talks.talks.enums.Status;
import io.redbee.product.conf.ms.talks.talks.model.Talk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TalkCreationService {
    private final TalkDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(TalkCreationService.class);

    public TalkCreationService(TalkDao dao) {
        this.dao = dao;
    }

    public Talk create(
            Boolean redbee_employee,
            String reference,
            String talk_name,
            String talk_topic,
            String talk_description,
            String speaker_name,
            String speaker_email
    ) {
        Talk talk = new TalkBuilder()
                .redbee_employee(redbee_employee)
                .reference(reference)
                .talk_name(talk_name)
                .talk_topic(talk_topic)
                .talk_description(talk_description)
                .speaker_name(speaker_name)
                .speaker_email(speaker_email)
                .creation_date(LocalDateTime.now())
                .build();

        return talk.copyId(save(talk));
    }

    public Integer save(Talk talk) {
        int id = dao.save(talk);
        LOGGER.info("save: talk {} saved", id);
        return id;

    }


}
