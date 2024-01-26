package ru.weathercities.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ResponseBuilder {

    public static <T> ResponseEntity<Response<T>> success(T data) {
        return ResponseEntity.ok(Response.<T>builder().data(data).success(true).build());
    }

    public static <T> ResponseEntity<Response<T>> success() {
        return ResponseEntity.ok(Response.<T>builder().data(null).success(true).build());
    }
}
