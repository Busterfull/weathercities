package ru.weathercities.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.weathercities.dto.CityDto;
import ru.weathercities.entity.Temperature;
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

    public List<CityDto> getDataWeatherWithDate(String nameCity, LocalDate date) {
        return cityRepository.getWeatherWithDate(nameCity, date).stream()
                .map(c -> {
                    CityDto city = new CityDto();
                    city.setCityName(c.getLeft());
                    city.setDate(c.getMiddle());
                    city.setTemperature(c.getRight());
                    return city;
                }).toList();
    }
    public Object getDataWeather(String nameCity) {
        return cityRepository.getWeatherLast(nameCity, PageRequest.of(0, 1)).get()
                .map(c -> {
                    CityDto city = new CityDto();
                    city.setCityName(c.getLeft());
                    city.setDate(c.getMiddle());
                    city.setTemperature(c.getRight());
                    return city;
                });
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
