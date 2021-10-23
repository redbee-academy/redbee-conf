package io.redbee.product.conf.ms.conferences.service;

import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateAlreadyExistsException;
import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import io.redbee.product.conf.ms.conferences.validations.ConferenceValidations;


@Service
public class ConferenceService {
        private final ConferenceDao conferenceDao;
        private final ConferenceValidations validations;

        private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceService.class);

        public ConferenceService(ConferenceDao conferenceDao, ConferenceValidations validations) {
            this.conferenceDao = conferenceDao;
            this.validations = validations;
        }
        public Conference create(Conference conference){
            validations.validateStartDateIsNotBeforeToday(conference.getStartDate());
            validations.validateStartDateAlreadyExists(conference.getStartDate());
            validations.validateEndDateIsNotBeforeStartDate(conference.getStartDate(),conference.getEndDate());
            int id = save(conference);
            return conference.copyId(id);
        }

    public int save(Conference conference) {
            int id = conferenceDao.save(conference);
            LOGGER.info("conference: conference {} saved", id);
            return id;
        }

}
