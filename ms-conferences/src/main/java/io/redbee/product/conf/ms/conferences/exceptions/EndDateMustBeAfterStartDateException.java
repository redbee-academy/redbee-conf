package io.redbee.product.conf.ms.conferences.exceptions;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class EndDateMustBeAfterStartDateException extends ResponseStatusException {
    public EndDateMustBeAfterStartDateException(LocalDateTime date) {
        super(UNPROCESSABLE_ENTITY, "end date must me after start date");
    }
}
