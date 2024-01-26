package ru.weathercities.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.weathercities.services.WeatherService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "scheduler.enabled", havingValue = "true")
public class TaskScheduler {

    private final WeatherService weatherService;

    @Scheduled(cron = "0 0 0 * * ?", zone = "GMT+3:00")
    public void getDataFromWeatherApi() {
        weatherService.getDataWeatherTimer();
    }
}
