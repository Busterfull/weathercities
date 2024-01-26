package ru.weathercities.outputs;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.weathercities.dto.OpenWeatherMapDto.OpenWeatherMapDto;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapService {

    private final RestTemplate restTemplate;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=e9b88643e90db7d14df049a1c56383ec";

    public Double getWeather(String lat, String lon) {  //Получаем в кельвинах
        String url = API_URL.replace("{lat}", lat).replace("{lon}", lon);
        OpenWeatherMapDto owm = restTemplate.getForEntity(url, OpenWeatherMapDto.class).getBody();
        return owm.getMain().getTemp() - 273;
    }
}
