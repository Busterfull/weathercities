package ru.weathercities.input.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.weathercities.dto.CityDto;
import ru.weathercities.http.Response;
import ru.weathercities.http.ResponseBuilder;
import ru.weathercities.services.WeatherService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<Response<List<CityDto>>> getWeatherCity(@RequestParam(name = "city") String city,
                                                           @RequestParam(name = "date") LocalDate date) {
        List<CityDto> c = weatherService.getDataWeatherWithDate(city, date);
        return ResponseBuilder.success(c);
    }

    @GetMapping("/{city}")
    public ResponseEntity<Response<Object>> getWeatherCity(@PathVariable String city) {
        return ResponseBuilder.success(weatherService.getDataWeather(city));
    }

}
