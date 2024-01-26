package ru.weathercities.http;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<Response<T>> success(T data) {
        return ResponseEntity.ok(Response.<T>builder().data(data).success(true).build());
    }
}
