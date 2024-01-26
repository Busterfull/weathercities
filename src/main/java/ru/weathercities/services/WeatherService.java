package ru.weathercities.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.weathercities.dto.CityDto;
import ru.weathercities.entity.Temperature;
import ru.weathercities.exceptions.WeatherCitiesExceptions;
import ru.weathercities.outputs.AccuweatherService;
import ru.weathercities.outputs.OpenMeteoService;
import ru.weathercities.outputs.OpenWeatherMapService;
import ru.weathercities.repository.CityRepository;
import ru.weathercities.repository.WeatherRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final OpenWeatherMapService openWeatherMapService;
    private final AccuweatherService accuweatherService;
    private final WeatherRepository weatherRepository;
    private final OpenMeteoService openMeteoService;
    private final CityRepository cityRepository;

    public List<Triple<String, LocalDate, Double>> getDataWeatherWithDate(String nameCity, LocalDate date) {
        return cityRepository.getWeatherWithDate(nameCity, date);
    }
    public Triple<String, LocalDate, Double> getDataWeather(String nameCity) {
        return cityRepository.getWeatherLast(nameCity, PageRequest.of(0, 1)).get()
                .findFirst()
                .orElseThrow(() -> new WeatherCitiesExceptions("Нет данных"));
    }

    public void getDataWeatherTimer() {
        cityRepository.findAll().forEach(city -> {
            Double owp = openWeatherMapService.getWeather(city.getLatitude(), city.getLongitude());
            Double op = openMeteoService.getWeather(city.getLatitude(), city.getLongitude());
            Double aw = accuweatherService.getWeather(city.getCode());
            Double avg = (owp + op + aw) / 3;
            Temperature t = new Temperature();
            t.setCityId(city.getId());
            t.setLocalDate(LocalDate.now());
            t.setTemperature(avg);
            t.setTime(LocalTime.now());

            weatherRepository.save(t);
        });
    }
}
