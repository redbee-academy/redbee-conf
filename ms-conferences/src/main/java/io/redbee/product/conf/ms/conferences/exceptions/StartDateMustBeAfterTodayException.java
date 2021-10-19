package io.redbee.product.conf.ms.conferences.exceptions;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import org.springframework.web.server.ResponseStatusException;

public class StartDateMustBeAfterTodayException extends ResponseStatusException{
    public StartDateMustBeAfterTodayException(){
        super(UNPROCESSABLE_ENTITY, "date must be after today");
    }

}
