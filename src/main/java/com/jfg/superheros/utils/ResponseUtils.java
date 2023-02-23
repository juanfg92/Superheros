package com.jfg.superheros.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public final class ResponseUtils {


    public static <T> ResponseEntity<T> createResponseEntity(HttpStatus status, String message) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("status", status.value());
        bodyMap.put("message", message);

        return new ResponseEntity<>((T) bodyMap, status);
    }


    public static <T> ResponseEntity<T> createResponseEntity(HttpStatus status, String message, T content) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("status", status.value());
        bodyMap.put("message", message);
        bodyMap.put("content", content);

        return new ResponseEntity<>((T) bodyMap, status);
    }


}
