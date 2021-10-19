package io.redbee.product.conf.ms.conferences.exceptions;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

public class StartDateAlreadyExistsException extends ResponseStatusException {
    public StartDateAlreadyExistsException(LocalDateTime startDate) {
        super(UNPROCESSABLE_ENTITY, "conf with date " + startDate + " already exists");
    }
}
