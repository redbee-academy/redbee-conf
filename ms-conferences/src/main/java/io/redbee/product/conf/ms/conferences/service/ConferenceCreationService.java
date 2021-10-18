package io.redbee.product.conf.ms.conferences.service;

import io.redbee.product.conf.ms.conferences.Dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.builder.ConferenceBuilder;
import io.redbee.product.conf.ms.conferences.enums.Status;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConferenceCreationService {
        private final ConferenceDao conferenceDao;

        private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceCreationService.class);

        public ConferenceCreationService(ConferenceDao conferenceDao) {
            this.conferenceDao = conferenceDao;
        }

        public Conference create(String name,
                LocalDate startDate,
                LocalDate endDate,
                String description,
                Status visibility) {
            Conference conference = buildWith(name,startDate,endDate,description,visibility);
            int id = save(conference);

            return conference.copyId(id);
        }

        private Conference buildWith(String name,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     String description,
                                     Status visibility) {
            return new ConferenceBuilder()
                    .name(name)
                    .startDate(startDate)
                    .endDate(endDate)
                    .description(description)
                    .visibility(visibility)
                    .build();
        }


        private int save(Conference conference) {
            int id = conferenceDao.save(conference);
            LOGGER.info("conference: conference {} saved", id);
            return id;
        }

}
