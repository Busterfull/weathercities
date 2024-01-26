package ru.weathercities.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CityDto {
    private String cityName;
    private LocalDate date;
    private Double temperature;
}
