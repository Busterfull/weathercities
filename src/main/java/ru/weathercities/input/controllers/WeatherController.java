package ru.weathercities.input.controllers;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Triple;
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
        List<CityDto> listData = weatherService.getDataWeatherWithDate(city, date).stream()
                .map(c -> {
                    CityDto cityDto = new CityDto();
                    cityDto.setCityName(c.getLeft());
                    cityDto.setDate(c.getMiddle());
                    cityDto.setTemperature(c.getRight());
                    return cityDto;
                }).toList();
         return ResponseBuilder.success(listData);
    }

    @GetMapping("/{city}")
    public ResponseEntity<Response<CityDto>> getWeatherCity(@PathVariable String city) {
        Triple<String, LocalDate, Double> dataWeather = weatherService.getDataWeather(city);
        CityDto cityDto = new CityDto();
        cityDto.setCityName(dataWeather.getLeft());
        cityDto.setDate(dataWeather.getMiddle());
        cityDto.setTemperature(dataWeather.getRight());
        return ResponseBuilder.success(cityDto);
    }

}
