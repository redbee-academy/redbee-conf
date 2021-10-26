package io.redbee.product.conf.ms.conferences.service;

import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateAlreadyExistsException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.validations.ConferenceValidations;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

public class ConferenceServiceUpdateTests {

    @Test
    void testUpdateConferenceSuccessfully() {
        // Given
        final var conferenceDao = Mockito.mock(ConferenceDao.class);
        final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
        final var service = new ConferenceService(conferenceDao, conferenceValidations);

        final var newConference = ConferenceFactory.getConference();
        newConference.setName("New name");
        newConference.setDescription("New description");
        newConference.setStatus(true);
        newConference.setStartDate(LocalDateTime.of(2045, 10, 28, 12, 30, 0));
        newConference.setEndDate(LocalDateTime.of(2045, 10, 29, 0, 0, 0));

        final var currentConference = ConferenceFactory.getConference();

        Mockito.when(conferenceDao.getById(Mockito.eq(currentConference.getId())))
                .thenReturn(Optional.of(currentConference));

        Mockito.doNothing().when(conferenceValidations)
                .validateEndDateIsNotBeforeStartDate(Mockito.any(), Mockito.any());

        Mockito.doNothing().when(conferenceValidations)
                .validateStartDateAlreadyExists(Mockito.any());

        Mockito.doNothing().when(conferenceValidations)
                .validateStartDateIsNotBeforeToday(Mockito.any());

        final var expected = newConference.copyId(newConference.getId());
        expected.setName(currentConference.getName());
        Mockito.when(conferenceDao.update(Mockito.eq(expected)))
                .thenReturn(expected);

        // When
        final var actual = service.update(newConference);

        // Then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testUpdateConferenceThatDoesNotExist() {
        // Given
        final var conferenceDao = Mockito.mock(ConferenceDao.class);
        final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
        final var service = new ConferenceService(conferenceDao, conferenceValidations);
        final var newConference = ConferenceFactory.getConference();

        Mockito.when(conferenceDao.getById(Mockito.eq(newConference.getId())))
                .thenReturn(Optional.empty());

        // When
        final var thrown = Assertions.catchThrowable(() -> service.update(newConference));

        // Then
        Assertions.assertThat(thrown)
                .isExactlyInstanceOf(java.util.NoSuchElementException.class);
    }

    @Test
    void testUpdateConferenceStartDateIsOlderThanTodayFails() {
        // Given
        final var conferenceDao = Mockito.mock(ConferenceDao.class);
        final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
        final var service = new ConferenceService(conferenceDao, conferenceValidations);

        final var newConference = ConferenceFactory.getConference();
        newConference.setStartDate(LocalDateTime.of(2020, 10, 28, 12, 30, 0));

        Mockito.when(conferenceDao.getById(Mockito.eq(newConference.getId())))
                .thenReturn(Optional.of(newConference));

        Mockito.doThrow(new StartDateMustBeAfterTodayException())
                .when(conferenceValidations)
                .validateStartDateIsNotBeforeToday(Mockito.any());

        // When
        final var thrown = Assertions.catchThrowable(() -> service.update(newConference));

        // Then
        Assertions.assertThat(thrown)
                .isExactlyInstanceOf(StartDateMustBeAfterTodayException.class);
    }

    @Test
    void testWhenUpdateConferenceEndDateIsBeforeStartDateFails() {
        // Given
        final var conferenceDao = Mockito.mock(ConferenceDao.class);
        final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
        final var service = new ConferenceService(conferenceDao, conferenceValidations);

        final var newConference = ConferenceFactory.getConference();
        newConference.setStartDate(LocalDateTime.of(2045, 10, 28, 12, 30, 0));
        newConference.setEndDate(LocalDateTime.of(2044, 10, 29, 0, 0, 0));

        Mockito.when(conferenceDao.getById(Mockito.eq(newConference.getId())))
                .thenReturn(Optional.of(newConference));

        Mockito.doNothing().when(conferenceValidations)
                .validateStartDateIsNotBeforeToday(Mockito.any());

        Mockito.doNothing().when(conferenceValidations)
                .validateStartDateAlreadyExists(Mockito.any());

        final var errorMsg = "Some error";
        Mockito.doThrow(new EndDateMustBeAfterStartDateException(errorMsg)).when(conferenceValidations)
                .validateEndDateIsNotBeforeStartDate(Mockito.eq(newConference.getStartDate()),
                        Mockito.eq(newConference.getEndDate()));

        // When
        final var thrown = Assertions.catchThrowable(() -> service.update(newConference));

        // Then
        Assertions.assertThat(thrown)
                .isExactlyInstanceOf(EndDateMustBeAfterStartDateException.class)
                .hasMessage(errorMsg);
    }

}
