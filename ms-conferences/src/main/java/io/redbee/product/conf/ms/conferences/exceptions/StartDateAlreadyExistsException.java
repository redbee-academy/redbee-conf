package io.redbee.product.conf.ms.conferences.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class StartDateAlreadyExistsException extends RuntimeException {
    public StartDateAlreadyExistsException(LocalDateTime startDate) {
        super("Conf with date " + startDate + " already exists");
    }
}
