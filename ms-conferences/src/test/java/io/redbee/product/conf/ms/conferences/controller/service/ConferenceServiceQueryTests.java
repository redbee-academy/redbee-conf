package io.redbee.product.conf.ms.conferences.controller.service;

import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import io.redbee.product.conf.ms.conferences.validations.ConferenceValidations;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ConferenceServiceQueryTests {
        @Test
        void testGetConferenceVisible() {
            // Given
            final var conferenceDao = Mockito.mock(ConferenceDao.class);
            final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
            final var service = new ConferenceService(conferenceDao, conferenceValidations);
            final var expected = ConferenceFactory.getConference();

            Mockito.when(conferenceDao.getByStatus(Mockito.eq(true)))
                    .thenReturn(Optional.of(expected));

            // When
            final var actual = service.getConfVisible();

            // Then
            Assertions.assertThat(actual).isEqualTo(expected);
        }

        @Test
        void testGetConferenceByNonExistingIdThrowsNoSuchElementException() {
            // Given
            final var conferenceDao = Mockito.mock(ConferenceDao.class);
            final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
            final var service = new ConferenceService(conferenceDao, conferenceValidations);

            Mockito.when(conferenceDao.getByStatus(false))
                    .thenReturn(Optional.empty());

            // When
            final var thrown = Assertions.catchThrowable(() -> service.getConfVisible());

            // Then
            Assertions.assertThat(thrown)
                    .isExactlyInstanceOf(java.util.NoSuchElementException.class);
        }

}
