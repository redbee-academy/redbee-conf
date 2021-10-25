package io.redbee.product.conf.ms.conferences.dao;

import io.redbee.product.conf.ms.conferences.exceptions.RepositoryException;
import io.redbee.product.conf.ms.conferences.mapper.ConferenceRowMapper;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.shared.util.LocalDateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.dao.EmptyResultDataAccessException;


import java.time.LocalDateTime;
import java.util.*;


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


    private static final String getQuery = "SELECT " +
            "id, " +
            "name, " +
            "volume," +
            "start_date, " +
            "end_date, " +
            "description, " +
            "status " +
            "FROM conferences";

    private static final String volumeQuery = "SELECT " +
            "volume " +
            "FROM conferences ORDER BY volume DESC LIMIT 1";

    public Optional<Integer> getConferenceVolume( ) {
        try {
            Optional<Integer> result = Optional.ofNullable(
                    template.queryForObject(
                            volumeQuery,
                            Collections.emptyMap(),
                            Integer.class
                    )
                    );
            LOGGER.info("getById: user found: {}", result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("Volume not found  " );
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getConferenceVolume: error {} searching volume  ", e.getMessage());
            throw new RepositoryException();
        }
    }


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
        params.addValue("volume",conference.getVolume());
        params.addValue("start_date", conference.getStartDate());
        params.addValue("end_date", conference.getEndDate());
        params.addValue("description", conference.getDescription());
        params.addValue("status", conference.getStatus());

        return params;
    }

    public List<Conference> getByStartDate(LocalDateTime start_date) {
        try {
            List<Conference> result =
                    template.query(
                            getQuery + " WHERE start_date = :start_date",
                            Map.of("start_date", start_date),
                            new ConferenceRowMapper()
                    );
            LOGGER.info("getByStartDate: conf found {}", result);
            return result;
        } catch (DataAccessException e) {
            LOGGER.info("getByStartDate: error {} searching conf with date: {}", e.getMessage(), start_date);
            throw new RepositoryException();
        }
    }

}

