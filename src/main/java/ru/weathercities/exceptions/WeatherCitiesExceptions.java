package ru.weathercities.exceptions;

import lombok.Getter;

@Getter
public class WeatherCitiesExceptions extends RuntimeException {

    private final String text;

    public WeatherCitiesExceptions(String text) {
        this.text = text;
    }
}
