package io.redbee.product.conf.ms.conferences.controller.factory;

import io.redbee.product.conf.ms.conferences.models.Conference;

import java.time.LocalDateTime;



public class ConferenceFactory {
    public static Conference getConference() {
        LocalDateTime startDate = LocalDateTime.parse("2021-11-25T22:17:52");
        LocalDateTime endDate = LocalDateTime.parse("2021-11-26T22:17:52");
        return new Conference(1,
                "Redbee conf vol. 5",
                startDate,
                endDate,
                "hello, welcome!",
                false);
    }
}
