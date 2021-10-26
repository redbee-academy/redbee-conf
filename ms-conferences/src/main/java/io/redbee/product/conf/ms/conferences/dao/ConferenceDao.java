package io.redbee.product.conf.ms.conferences.dao;

import io.redbee.product.conf.ms.conferences.exceptions.RepositoryException;
import io.redbee.product.conf.ms.conferences.mapper.ConferenceRowMapper;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.shared.util.LocalDateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

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
            "start_date, " +
            "end_date, " +
            "description, " +
            "status " +
            "FROM conferences";

    private static final String updateQuery = "" +
            "UPDATE conferences SET name=:name," +
            " start_date= :start_date, " +
            "end_date= :end_date, " +
            "description= :description," +
            "status= :status " +
             "WHERE id = :id";

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
    public Optional<Conference> getByStatus(Boolean isVisible) {
        try {
            Optional<Conference> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE status = :isVisible",
                            Map.of("isVisible", isVisible),
                            new ConferenceRowMapper()
                    )
            );
            LOGGER.info("getByStatus: conference found: {}", result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("getByStatus: conference with status {} not found", isVisible);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getByStatus: error {} searching conference with status {}", e.getMessage(), isVisible);
            throw new RepositoryException();
        }
    }

    public Optional<Conference> getById(Integer id) {
        try {
            Optional<Conference> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE id = :id",
                            Map.of("id", id),
                            new ConferenceRowMapper()
                    )
            );
            LOGGER.info("getById: conference found: {}", result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("getById: conference with id {} not found", id);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getById: error {} searching conference with id: {}", e.getMessage(), id);
            throw new RepositoryException();
        }
    }

    public Conference update(Conference conference){
        try{
            template.update(updateQuery, conferenceToParamMap(conference));
            LOGGER.info("update: conference {} updated", conference.getId());
            return conference;
        } catch (Exception e){
            LOGGER.info("update: error {},updating conference {}", e.getMessage(), conference.getId());
        }
        return conference;
    }
}

