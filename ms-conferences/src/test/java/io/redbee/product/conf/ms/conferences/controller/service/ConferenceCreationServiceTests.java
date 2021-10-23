package io.redbee.product.conf.ms.conferences.controller.service;


import io.redbee.product.conf.ms.conferences.controller.factory.ConferenceFactory;
import io.redbee.product.conf.ms.conferences.dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.exceptions.EndDateMustBeAfterStartDateException;
import io.redbee.product.conf.ms.conferences.exceptions.StartDateMustBeAfterTodayException;
import io.redbee.product.conf.ms.conferences.models.Conference;
import io.redbee.product.conf.ms.conferences.service.ConferenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.LocalDateTime;



import static org.junit.jupiter.api.Assertions.assertEquals;

//class ConferenceCreationServiceTests {
//
//    ConferenceDao conferenceDao =  Mockito.mock(ConferenceDao.class);
//    ConferenceService service = new ConferenceService(conferenceDao);
//
//    @Test
//    @DisplayName("Get equal conference")
//    void getCreateConferenceTest(){
//
//            Conference conf = ConferenceFactory.getConference();
//
//
//            Mockito.when(conferenceDao.save(conf)).thenReturn(conf.getId());
//
//
//            Conference result = service.create(
//                    conf.getName(),
//                    conf.getStartDate(),
//                    conf.getEndDate(),
//                    conf.getDescription(),
//                    conf.getStatus());
//
//
//            assertEquals(conf.getName(), result.getName());
//            assertEquals(conf.getStartDate(),result.getStartDate());
//            assertEquals(conf.getEndDate(),result.getEndDate());
//            assertEquals(conf.getStatus(),result.getStatus());
//
//        }
//
//        @Test
//        @DisplayName("Validate StartDate")
//        void validateStartDateTest(){
//
//            LocalDateTime startDate = LocalDateTime.parse("2021-09-25T22:17:52");
//            Conference conf = ConferenceFactory.getConference();
//            conf.setStartDate(startDate);
//
//
//            Mockito.when(conferenceDao.save(conf)).thenThrow(StartDateMustBeAfterTodayException.class);
//
//
//
//            Assertions.assertThrows(StartDateMustBeAfterTodayException.class, () -> {
//                service.create(
//                        conf.getName(),
//                        conf.getStartDate(),
//                        conf.getEndDate(),
//                        conf.getDescription(),
//                        conf.getStatus());
//            });
//
//        }
//
//    @Test
//    @DisplayName("Validate EndDate")
//    void validateEndDateTest(){
//
//        LocalDateTime endDate = LocalDateTime.parse("2021-09-25T22:17:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setEndDate(endDate);
//
//
//        Mockito.when(conferenceDao.save(conf)).thenReturn(conf.getId());
//
//        Assertions.assertThrows(EndDateMustBeAfterStartDateException.class, () -> {
//            service.create(
//                    conf.getName(),
//                    conf.getStartDate(),
//                    conf.getEndDate(),
//                    conf.getDescription(),
//                    conf.getStatus());
//        });
//
//    }
//
//    @Test
//    @DisplayName("Validate end date time 1")
//    void validateEndDateTest2(){
//        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
//        LocalDateTime endDate = LocalDateTime.parse("2021-11-25T21:45:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setStartDate(startDate);
//        conf.setEndDate(endDate);
//
//        Mockito.when(conferenceDao.save(conf)).thenReturn(conf.getId());
//
//        Assertions.assertThrows(EndDateMustBeAfterStartDateException.class, () -> {
//            service.create(
//                    conf.getName(),
//                    conf.getStartDate(),
//                    conf.getEndDate(),
//                    conf.getDescription(),
//                    conf.getStatus());
//        });
//
//    }
//
//    @Test
//    @DisplayName("Validate end date time 2")
//    void validateEndDateTest3(){
//        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
//        LocalDateTime endDate = LocalDateTime.parse("2021-11-25T22:45:52");
//        Conference conf = ConferenceFactory.getConference();
//        conf.setStartDate(startDate);
//        conf.setEndDate(endDate);
//
//        Mockito.when(conferenceDao.save(conf)).thenReturn(conf.getId());
//
//        Assertions.assertThrows(EndDateMustBeAfterStartDateException.class, () -> {
//            service.create(
//                    conf.getName(),
//                    conf.getStartDate(),
//                    conf.getEndDate(),
//                    conf.getDescription(),
//                    conf.getStatus());
//        });
//
//    }
//
//}
