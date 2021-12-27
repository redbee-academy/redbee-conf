package io.redbee.product.conf.ms.talks.talks.service;

import io.redbee.product.conf.ms.talks.talks.builder.TalkBuilder;
import io.redbee.product.conf.ms.talks.talks.dao.TalkDao;
import io.redbee.product.conf.ms.talks.talks.model.Talk;
import io.redbee.product.conf.ms.talks.talks.model.TalkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TalkService {
    private final TalkDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(TalkService.class);

    public TalkService(TalkDao dao) {
        this.dao = dao;
    }

    public Talk create(
            Boolean redbee_employee,
            String reference,
            String talk_name,
            String talk_topic,
            String talk_description,
            String speaker_name,
            String speaker_email,
            TalkStatus status,
            Integer conference_id
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
            .conference_id(conference_id)
            .status(status)
            .build();

        return talk.copyId(save(talk));
    }

    public Integer save(Talk talk) {
        int id = dao.save(talk);
        LOGGER.info("save: talk {} saved", id);
        return id;

    }

    public List<Talk> getByConferenceId(Integer conferenceId, TalkStatus status) {
        return dao.getByConferenceId(conferenceId, status);
    }

    public void updateStatus(Integer id, TalkStatus status) {
        dao.updateStatus(id, status);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }
}
