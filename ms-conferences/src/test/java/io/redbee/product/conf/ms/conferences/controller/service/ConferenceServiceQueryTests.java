package io.redbee.product.conf.ms.conferences.controller.service;

import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import io.redbee.product.conf.ms.conferences.validations.ConferenceValidations;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class ConferenceServiceQueryTests {
        @Test
        void testGetConferenceVisible() {
            // Given
            final var conferenceDao = Mockito.mock(ConferenceDao.class);
            final var conferenceValidations = Mockito.mock(ConferenceValidations.class);
            final var service = new ConferenceService(conferenceDao, conferenceValidations);
            final var expected = Collections.singletonList(ConferenceFactory.getConference());

            Mockito.when(conferenceDao.getByStatus(Mockito.eq(true)))
                    .thenReturn(expected);

            // When
            final var actual = service.getConf(true);

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
                    .thenReturn(emptyList());

            // When
            final var actual = service.getConf(false);

            // Then
            Assertions.assertThat(actual).isEqualTo(emptyList());
        }

}
