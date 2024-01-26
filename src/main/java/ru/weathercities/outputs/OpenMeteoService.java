package ru.weathercities.outputs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.weathercities.dto.openmeteo.OpenMeteoDto;

@Service
@RequiredArgsConstructor
public class OpenMeteoService {

    private final RestTemplate restTemplate;
    private final String API_URL =
            "https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lon}&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";

    public Double getWeather(String lat, String lon) {
        String url = API_URL.replace("{lat}", lat).replace("{lon}", lon);
        OpenMeteoDto om = restTemplate.getForEntity(url, OpenMeteoDto.class).getBody();
        return om.getCurrent().getTemperature();
    }
}
