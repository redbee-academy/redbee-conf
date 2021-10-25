package io.redbee.product.conf.ms.conferences.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ResponseStatus(NOT_FOUND)
public class VolumeNotFoundException extends RuntimeException{

    public VolumeNotFoundException(){
        super("volume not found");
    }
}
