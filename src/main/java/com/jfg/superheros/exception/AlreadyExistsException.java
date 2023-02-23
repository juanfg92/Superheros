package com.jfg.superheros.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends RestException {

    public static final HttpStatus STATUS = HttpStatus.CONFLICT;


    public AlreadyExistsException(String errorMessage) {
        super(STATUS, errorMessage);
    }

}
