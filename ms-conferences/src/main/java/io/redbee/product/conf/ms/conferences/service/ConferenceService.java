package io.redbee.product.conf.ms.conferences.service;

import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.exceptions.VolumeNotFoundException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        validations.validateEndDateIsNotBeforeStartDate(conference.getStartDate(),conference.getEndDate());
        validations.validateStartDateAlreadyExists(conference.getStartDate());
        int id = save(conference);
        return conference.copyId(id);
    }


    public int save(Conference conference) {
        int id = conferenceDao.save(conference);
        LOGGER.info("conference: conference {} saved", id);
        return id;
    }

    public Integer getConferenceVolume(){
        Integer volume = conferenceDao.getConferenceVolume()
                .orElseThrow(() -> new VolumeNotFoundException());
        LOGGER.info("volume: volume {} found",volume);
        return volume;
    }

    public List<Conference> getConferences() {
        return conferenceDao.get();
    }

    public Optional<Conference> getCurrentConf(Boolean visible) {
        return conferenceDao.getCurrent(visible);
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
                conference.getVolume(),
                conference.getStartDate(),
                conference.getEndDate(),
                conference.getDescription(),
                conference.getStatus()
        );
        isConferenceValidForUpdate(conference);
        return conferenceDao.update(updated);
    }

    public void delete(Integer id) {
        conferenceDao.delete(id);
    }

    private void isConferenceValidForUpdate(Conference conference) {
        validations.validateStartDateIsNotBeforeToday(conference.getStartDate());
        validations.validateEndDateIsNotBeforeStartDate(conference.getStartDate(), conference.getEndDate());
    }
}
