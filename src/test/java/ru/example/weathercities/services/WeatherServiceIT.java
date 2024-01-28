package ru.example.weathercities.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.weathercities.WeatherCitiesApplication;
import ru.weathercities.outputs.AccuweatherService;
import ru.weathercities.outputs.OpenMeteoService;
import ru.weathercities.outputs.OpenWeatherMapService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = WeatherCitiesApplication.class)
public class WeatherServiceIT {

    @Autowired
    private OpenWeatherMapService openWeatherMapService;
    @Autowired
    private AccuweatherService accuweatherService;
    @Autowired
    private OpenMeteoService openMeteoService;

    /**
     @see OpenMeteoService#getWeather(String, String)
     */
    @Test
    @DisplayName("OpenMeteo IT")
    void openMeteoTest() {
        String lat = "55.76";
        String lon = "36.37";
        Double temp = openMeteoService.getWeather(lat, lon);
        assertNotNull(temp);
    }

    @Test
    @DisplayName("OpenWeatherMap IT")
    void openWeatherTest() {
        String lat = "55.76";
        String lon = "36.37";
        Double temp = openWeatherMapService.getWeather(lat, lon);
        assertNotNull(temp);
    }

    @Test
    @DisplayName("Аccuweather IT")
    void accuweatherTest() {
        Double temp = accuweatherService.getWeather("3369271");
        assertNotNull(temp);
    }
}
