package com.jfg.superheros.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class RestException extends Exception {

    @Getter
    private final HttpStatus status;


    public RestException(HttpStatus status, String errorMessage) {
        super(errorMessage);
        this.status = status;
    }

}
