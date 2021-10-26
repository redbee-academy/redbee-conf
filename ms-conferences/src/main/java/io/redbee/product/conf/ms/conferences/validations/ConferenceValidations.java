package io.redbee.product.conf.ms.conferences.validations;

import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateAlreadyExistsException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
@Component
public class ConferenceValidations {
    @Autowired
    final ConferenceDao conferenceDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceService.class);

    public ConferenceValidations(ConferenceDao conferenceDao) {
        this.conferenceDao = conferenceDao;
    }

    public void validateStartDateIsNotBeforeToday(LocalDateTime startDate){
        if(startDate.isBefore(LocalDateTime.now())){
            LOGGER.info("validateStartDateIsNoteBeforeToday: cannot set {} as a start date, must be after today", startDate);
            throw new StartDateMustBeAfterTodayException();
        }
    }

    public void validateEndDateIsNotBeforeStartDate(LocalDateTime startDate, LocalDateTime endDate){
        if(endDate.isBefore(startDate)){
            LOGGER.info("validateEndDateIsNotBeforeStartDate: cannot set {} as an end date, must be after start date", endDate);
            throw new EndDateMustBeAfterStartDateException("End date must be after or the same date at the start date");
        }else if(startDate.getHour() == endDate.getHour() &&
                sameDay(startDate,endDate) &&
                sameMonth(startDate,endDate) &&
                sameYear(startDate,endDate)){
            LOGGER.info("validateEndDateIsNotBeforeStartDate: cannot set {} as an end date, because the hour is the same at start" +
                    " date", endDate);
            throw new EndDateMustBeAfterStartDateException("End date must be after the start date at least one hour");
        }
    }

    public void validateStartDateAlreadyExists(LocalDateTime startDate) {
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
                .filter(conference -> !conference.getStatus().equals(true))
                .findFirst();
    }

    private boolean sameDay(LocalDateTime starDate, LocalDateTime endDate){
        return starDate.getDayOfMonth() == endDate.getDayOfMonth();
    }

    private boolean sameMonth(LocalDateTime startDate, LocalDateTime endDate){
        return startDate.getMonth() == endDate.getMonth();
    }

    private boolean sameYear(LocalDateTime startDate, LocalDateTime endDate){
        return startDate.getYear() == endDate.getYear();
    }
}
