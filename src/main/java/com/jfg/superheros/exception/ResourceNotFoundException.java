package com.jfg.superheros.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RestException {

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;


    public ResourceNotFoundException(String errorMessage) {
        super(STATUS, errorMessage);
    }

}
