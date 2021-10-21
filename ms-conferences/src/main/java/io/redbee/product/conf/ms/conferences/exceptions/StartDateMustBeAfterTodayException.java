package io.redbee.product.conf.ms.conferences.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class StartDateMustBeAfterTodayException extends RuntimeException{
    public StartDateMustBeAfterTodayException(){
        super("Start date must be after today date");
    }

}
