package com.jfg.superheros.exception;

import org.springframework.http.HttpStatus;

public class BadArgumentException extends RestException {

    public static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;


    public BadArgumentException(String errorMessage) {
        super(STATUS, errorMessage);
    }

}
