package io.redbee.product.conf.ms.conferences.dao;

import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.exceptions.RepositoryException;
import io.redbee.product.conf.ms.conferences.mapper.ConferenceRowMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class ConferenceDaoQueryTests {

    private static final String SQL = "SELECT " +
            "id, " +
            "name, " +
            "start_date, " +
            "end_date, " +
            "description, " +
            "status " +
            "FROM conferences" +
            " WHERE id = :id";

    @Test
    void testGetConferenceByIdSuccessfully() {
        // Given
        final var jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        final var dao = new ConferenceDao(jdbcTemplate);

        final var expected = ConferenceFactory.getConference();

        final var expectedMap = Map.of("id", expected.getId());
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.eq(SQL),
                Mockito.eq(expectedMap),
                Mockito.any(ConferenceRowMapper.class))
        ).thenReturn(expected);

        // When
        final var actual = dao.getById(expected.getId());

        // Then
        Assertions.assertThat(actual.isPresent()).isTrue();
        Assertions.assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    void testGetConferenceByIdShouldWrapNotFoundInEmptyOptional() {
        // Given
        final var jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        final var dao = new ConferenceDao(jdbcTemplate);
        final var id = 10;

        final var expectedMap = Map.of("id", id);
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.eq(SQL),
                Mockito.eq(expectedMap),
                Mockito.any(ConferenceRowMapper.class))
        ).thenThrow(new EmptyResultDataAccessException(1));

        // When
        final var actual = dao.getById(id);

        // Then
        Assertions.assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void testGetConferenceByIdShouldMapDataAccessExceptionToRepositoryException() {
        // Given
        final var jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        final var dao = new ConferenceDao(jdbcTemplate);
        final var id = 10;
        final var errorMsg = "Some error";

        final var expectedMap = Map.of("id", id);
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.eq(SQL),
                Mockito.eq(expectedMap),
                Mockito.any(ConferenceRowMapper.class))
        ).thenThrow(new DataAccessException(errorMsg) {});

        // When
        final var thrown = Assertions.catchThrowable(() -> dao.getById(id));

        // Then
        Assertions.assertThat(thrown)
                .isExactlyInstanceOf(RepositoryException.class)
                .hasMessageContaining("error communicating with repository");
    }

}
