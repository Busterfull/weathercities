package ru.example.weathercities.services;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.weathercities.entity.City;
import ru.weathercities.entity.Temperature;
import ru.weathercities.outputs.AccuweatherService;
import ru.weathercities.outputs.OpenMeteoService;
import ru.weathercities.outputs.OpenWeatherMapService;
import ru.weathercities.repository.CityRepository;
import ru.weathercities.repository.WeatherRepository;
import ru.weathercities.services.WeatherService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    private OpenWeatherMapService openWeatherMapService;
    @Mock
    private AccuweatherService accuweatherService;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private OpenMeteoService openMeteoService;
    @Mock
    private CityRepository cityRepository;

    private WeatherService undertest;

    @BeforeEach
    public void init() {
        undertest =
                new WeatherService(openWeatherMapService,
                        accuweatherService, weatherRepository, openMeteoService, cityRepository);
    }


    /**
    @see WeatherService#getDataWeatherWithDate(String, LocalDate)
    */
    @Test
    @DisplayName("With date")
    void getDataWeatherWithDateTest() {
        List<Triple<String, LocalDate, Double>> list =
                List.of(Triple.of("Moscow", LocalDate.of(2024,1, 25), 1.1),
                Triple.of("Moscow", LocalDate.of(2024,1, 25), 1.1),Triple.of("Moscow",
                                LocalDate.of(2024,1, 25), 1.1));

        given(cityRepository.getWeatherWithDate("Moscow", LocalDate.of(2024,1, 25)))
                .willReturn(list);
        undertest.getDataWeatherWithDate("Moscow", LocalDate.of(2024,1, 25));
    }

    /**
     @see WeatherService#getDataWeatherWithDate(String, LocalDate)
     */
    @Test
    @DisplayName("With date and null in DB")
    void getDataWeatherWithDateNullTest() {
        given(cityRepository.getWeatherWithDate("Moscow", LocalDate.of(2024,1, 25)))
                .willReturn(List.of());
        undertest.getDataWeatherWithDate("Moscow", LocalDate.of(2024,1, 25));
    }

    /**
    @see WeatherService#getDataWeather(String)
    */
    @Test
    @DisplayName("Last data")
    void getDataWeatherTest() {
        given(cityRepository.getWeatherLast("Moscow", PageRequest.of(0, 1)))
                .willReturn(new PageImpl<>(List.of(Triple.of("Moscow", LocalDate.of(2024,1, 25), 1.0))));
        undertest.getDataWeather("Moscow");
    }

    /**
    @see WeatherService#getDataWeatherTimer()
    */
    @Test
    @DisplayName("Survey")
    void getDataWeatherTimerTest() {
        City city = new City();
        city.setName("Moscow");
        city.setCountry("Russia");
        city.setLatitude("55.45");
        city.setLongitude("37.36");
        city.setCode("3369271");
        Temperature temperature = new Temperature();

        given(cityRepository.findAll()).willReturn(List.of(city));
        given(openWeatherMapService.getWeather(city.getLatitude(), city.getLongitude())).willReturn(1.0);
        given(openMeteoService.getWeather(city.getLatitude(), city.getLongitude())).willReturn(1.5);
        given(accuweatherService.getWeather(city.getCode())).willReturn(0.5);
        undertest.getDataWeatherTimer();
        verify(weatherRepository).save(temperature);
    }
}
