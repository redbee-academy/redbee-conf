package io.redbee.product.conf.ms.talks.talks.exceptions;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class RepositoryException extends ResponseStatusException {
    public RepositoryException() {
        super(INTERNAL_SERVER_ERROR, "error communicating with repository");
    }
}
