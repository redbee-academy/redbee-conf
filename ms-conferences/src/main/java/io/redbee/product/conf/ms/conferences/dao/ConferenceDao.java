package io.redbee.product.conf.ms.conferences.dao;

import io.redbee.product.conf.ms.conferences.models.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConferenceDao {
    private final NamedParameterJdbcTemplate template;

    public ConferenceDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceDao.class);

    private static final String insertQuery = "" +
            "INSERT INTO conferences (name, start_date, end_date, description, status) " +
            "VALUES (:name, :start_date, :end_date, :description, :status) ";


    public int save(Conference conference) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(insertQuery, conferenceToParamMap(conference), keyHolder);
            LOGGER.info("save: conference {} saved", conference.getName());

            return (int) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch (Exception e) {
            LOGGER.info("save: error {}, saving conference {}", e.getMessage(), conference.getName());
            throw new RuntimeException(); //definir si necesitamos una exception personalizada
        }
    }


    private MapSqlParameterSource conferenceToParamMap(Conference conference) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", conference.getId());
        params.addValue("name", conference.getName());
        params.addValue("start_date", conference.getStartDate());
        params.addValue("end_date", conference.getEndDate());
        params.addValue("description", conference.getDescription());
        params.addValue("status", conference.getVisibility());

        return params;
    }
}

