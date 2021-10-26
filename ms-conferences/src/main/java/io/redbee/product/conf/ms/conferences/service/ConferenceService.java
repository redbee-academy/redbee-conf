package io.redbee.product.conf.ms.conferences.service;

import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Conference create(String name, //TODO: revisar si es mejor pasar un objeto conference
                             LocalDateTime startDate,
                             LocalDateTime endDate,
                             String description, Boolean status) {
        Conference conference = buildWith(name, startDate, endDate, description, status);
        isConferenceValid(conference);
        int id = save(conference);
        return conference.copyId(id);
    }

        private Conference buildWith(String name,
                                     LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     String description,
                                     Boolean status) {
            return new ConferenceBuilder()
                    .name(name)
                    .startDate(startDate)
                    .endDate(endDate)
                    .description(description)
                    .visibility(status)
                    .build();
        }

    public int save(Conference conference) {
        int id = conferenceDao.save(conference);
        LOGGER.info("conference: conference {} saved", id);
        return id;
    }

    public Conference getConfVisible() {
            Conference conferenceFound = conferenceDao.getByStatus(true).orElseThrow();
            LOGGER.info("conference: conference found {}", conferenceFound);
            return conferenceFound;
    }

    public Conference getById(Integer id){
            Conference conferenceFound = conferenceDao.getById(id).orElseThrow();
            LOGGER.info("conference: conference found {}", conferenceFound);
            return conferenceFound;
    }

    public Conference update(Conference conference) {
        Conference toUpdate = conferenceDao.getById(conference.getId()).orElseThrow();
        Conference updated = new Conference(
                toUpdate.getId(),
                toUpdate.getName(),
                conference.getStartDate(),
                conference.getEndDate(),
                conference.getDescription(),
                conference.getStatus()
        );
        isConferenceValid(conference);
        return conferenceDao.update(updated);
    }

    private void isConferenceValid(Conference conference) {
        validations.validateStartDateIsNotBeforeToday(conference.getStartDate());
        System.out.println("Here2");
        validations.validateStartDateAlreadyExists(conference.getStartDate());
        System.out.println("here1");
        validations.validateEndDateIsNotBeforeStartDate(conference.getStartDate(), conference.getEndDate());
    }
}
