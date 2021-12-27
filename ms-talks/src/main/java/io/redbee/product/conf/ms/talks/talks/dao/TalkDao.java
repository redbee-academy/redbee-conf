package io.redbee.product.conf.ms.talks.talks.dao;
import io.redbee.product.conf.ms.talks.talks.exceptions.RepositoryException;
import io.redbee.product.conf.ms.talks.talks.mapper.TalkRowMapper;
import io.redbee.product.conf.ms.talks.talks.model.Talk;


import io.redbee.product.conf.ms.talks.talks.model.TalkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class TalkDao {

    private final NamedParameterJdbcTemplate template;

    public TalkDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TalkDao.class);

    private static final String insertQuery = "" +
            "INSERT INTO talks (redbee_employee, reference, talk_name, talk_topic, talk_description, creation_date, speaker_name, speaker_email, status, conference) " +
            "VALUES (:redbee_employee, :reference, :talk_name, :talk_topic, :talk_description, :creation_date, :speaker_name, :speaker_email, :status, :conference)";

    private static final String selectQuery = "" +
      "SELECT id, status, conference, talk_name, talk_topic, talk_description, speaker_name, speaker_email, redbee_employee, reference, creation_date " +
      "FROM talks";

    private static final String updateStatusByIdQuery = "" +
      "UPDATE talks SET status=:status WHERE id=:id";

    private static final String deleteByIdQuery = "" +
      "DELETE FROM talks WHERE id=:id";

    public Integer save (Talk talk) {
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

    public List<Talk> getByConferenceId(Integer conferenceId, TalkStatus status) {
        try {
            TalkRowMapper rowMapper = new TalkRowMapper();

            String query = selectQuery + " WHERE conference=:conference";
            Map<String, Object> params = new java.util.HashMap<>(Map.of("conference", conferenceId));

            if (status != null) {
                query += " AND status=:status";
                params.put("status", status.getValue());
            }

            List<Talk> result = template.query(query, params, rowMapper);

            LOGGER.info("getByConferenceId: Retrieved {} talks for conference {}, status {}", result.size(), conferenceId, status);

            return result;
        } catch (Exception e) {
            LOGGER.error("getByConferenceId: error querying talks for conference {} with status {}: {}", conferenceId, status, e.getMessage());
            throw new RepositoryException();
        }
    }

    public void updateStatus(Integer id, TalkStatus status) {
        try {
            template.update(updateStatusByIdQuery, Map.of("id", id, "status", status));
        } catch (Exception e) {
            LOGGER.error("updateStatus: error updating status of task {} to {}: {}", id, status, e.getMessage());
            throw new RepositoryException();
        }
    }

    public void delete(Integer id) {
        try {
            template.update(deleteByIdQuery, Map.of("id", id));
        } catch (Exception e) {
            LOGGER.error("delete: error deleting talk {}: {}", id, e.getMessage());
            throw new RepositoryException();
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
        params.addValue("status", talk.getStatus().getValue());
        params.addValue("conference", talk.getConference_id());

        return params;
    }
}
