package ru.weathercities.dto.openmeteo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OpenMeteoCurrentDto {
    @JsonProperty("temperature_2m")
    private Double temperature;
}
