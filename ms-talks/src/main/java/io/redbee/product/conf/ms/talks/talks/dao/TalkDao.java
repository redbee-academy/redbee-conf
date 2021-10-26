package io.redbee.product.conf.ms.talks.talks.dao;
import io.redbee.product.conf.ms.talks.talks.exceptions.RepositoryException;
import io.redbee.product.conf.ms.talks.talks.model.Talk;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TalkDao {

    private final NamedParameterJdbcTemplate template;

    public TalkDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TalkDao.class);

    private static final String insertQuery = "" +
            "INSERT INTO talks (redbee_employee, reference, talk_name, talk_topic, talk_description, creation_date, speaker_name, speaker_email)" +
            "VALUES (:redbee_employee, :reference, :talk_name, :talk_topic, :talk_description, :creation_date, :speaker_name, :speaker_email)";

    public Integer save (Talk talk){
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(insertQuery, talkToParamMap(talk), keyHolder);
            LOGGER.info("save: talk {} saved", talk.getTalk_name());

            return (int) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch (Exception e) {
            LOGGER.info("save: error {}, saving talk {}", e.getMessage(), talk.getTalk_name());
            throw new RuntimeException();
        }
    }

    private MapSqlParameterSource talkToParamMap(Talk talk) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", talk.getId());
        params.addValue("redbee_employee", talk.getRedbee_employee());
        params.addValue("reference", talk.getReference());
        params.addValue("talk_name", talk.getTalk_name());
        params.addValue("talk_topic", talk.getTalk_topic());
        params.addValue("talk_description", talk.getTalk_description());
        params.addValue("creation_date", talk.getCreation_date());
        params.addValue("speaker_name", talk.getSpeaker_name());
        params.addValue("speaker_email", talk.getSpeaker_email());

        return params;
    }


}
