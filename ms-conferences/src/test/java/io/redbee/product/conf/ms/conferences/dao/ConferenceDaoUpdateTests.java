package io.redbee.product.conf.ms.conferences.dao;

import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class ConferenceDaoUpdateTests {

    private static final String SQL =
            "INSERT INTO conferences (name, start_date, end_date, description, status) " +
                    "VALUES (:name, :start_date, :end_date, :description, :status) ";

    @Test
    void testUpdateConferenceSuccessfully() {
        // Given
        final var jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        final var dao = new ConferenceDao(jdbcTemplate);

        final var expected = ConferenceFactory.getConference();

        final var expectedMap = new MapSqlParameterSource(
                Map.of(
                        "id", expected.getId(),
                        "name", expected.getName(),
                        "start_date", expected.getStartDate(),
                        "end_date", expected.getEndDate(),
                        "description", expected.getDescription(),
                        "status", expected.getStatus()
                )
        );
        Mockito.when(jdbcTemplate.update(Mockito.eq(SQL), Mockito.eq(expectedMap))).thenReturn(1);

        // When
        final var actual = dao.update(expected);

        // Then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testUpdateConferenceShouldHandleExceptionAndReturnNonetheless() {
        // Given
        final var jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        final var dao = new ConferenceDao(jdbcTemplate);

        final var expected = ConferenceFactory.getConference();

        final var errorMsg = "Some error";
        Mockito.when(jdbcTemplate.update(Mockito.eq(SQL), Mockito.any(MapSqlParameterSource.class)))
                .thenThrow(new RuntimeException(errorMsg));

        // When
        final var actual = dao.update(expected);

        // Then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
