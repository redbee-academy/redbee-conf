package io.redbee.product.conf.ms.conferences.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;



import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ResponseStatus(UNPROCESSABLE_ENTITY)
public class EndDateMustBeAfterStartDateException extends RuntimeException {
    public EndDateMustBeAfterStartDateException() {
        super("End date must be after or the same date at the start date");
    }
}
