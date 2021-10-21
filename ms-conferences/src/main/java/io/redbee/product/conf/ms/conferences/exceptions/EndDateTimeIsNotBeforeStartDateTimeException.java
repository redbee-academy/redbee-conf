package io.redbee.product.conf.ms.conferences.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EndDateTimeIsNotBeforeStartDateTimeException extends RuntimeException {
    public EndDateTimeIsNotBeforeStartDateTimeException() {
        super("End date time must be after the start date time in the same day. At least one hour");
    }
}