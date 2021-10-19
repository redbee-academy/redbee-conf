package io.redbee.product.conf.ms.conferences.service;

import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateAlreadyExistsException;
import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ConferenceCreationService {
        private final ConferenceDao conferenceDao;

        private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceCreationService.class);

        public ConferenceCreationService(ConferenceDao conferenceDao) {
            this.conferenceDao = conferenceDao;
        }

        public Conference create(String name,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String description){
            Conference conference = buildWith(name,startDate,endDate,description);
            validateStartDateIsNotBeforeToday(conference.getStartDate());
            validateEndDateIsNotBeforeStartDate(conference.getStartDate(),conference.getEndDate());
            validateStartDateAlreadyExists(conference.getStartDate());
            int id = save(conference);
            return conference.copyId(id);
        }

        private Conference buildWith(String name,
                                     LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     String description) {
            return new ConferenceBuilder()
                    .name(name)
                    .startDate(startDate)
                    .endDate(endDate)
                    .description(description)
                    .visibility(false)
                    .build();
        }

    private void validateStartDateIsNotBeforeToday(LocalDateTime startDate){
        if(startDate.isBefore(LocalDateTime.now())){
            LOGGER.info("validateStartDateIsNoteBeforeToday: cannot set {} as a start date, must be after today", startDate);
            throw new StartDateMustBeAfterTodayException();
        }
    }

    private void validateEndDateIsNotBeforeStartDate(LocalDateTime startDate, LocalDateTime endDate){
        if(endDate.isBefore(startDate)){
            LOGGER.info("validateEndDateIsNotBeforeStartDate: cannot set {} as an end date, must be after start date", endDate);
            throw new EndDateMustBeAfterStartDateException(endDate);
        }
    }


        private void validateStartDateAlreadyExists(LocalDateTime startDate) {
            if (existsStartDate(startDate)) {
                LOGGER.info("validateAccountAlreadyExists: conf with date {} already exists", startDate);
                throw new StartDateAlreadyExistsException(startDate);
            }
            LOGGER.info("conf {} doesnt have a date yet", startDate);
        }

    private boolean existsStartDate(LocalDateTime startDate) {
        return getActiveByStartDate(startDate).isPresent();
    }

    public Optional<Conference> getActiveByStartDate(LocalDateTime startDate) {
        return conferenceDao.getByStartDate(startDate)
                .stream()
                .filter(conference -> !conference.getStatus().equals(false))
                .findFirst();
    }


    public int save(Conference conference) {
            int id = conferenceDao.save(conference);
            LOGGER.info("conference: conference {} saved", id);
            return id;
        }

}
