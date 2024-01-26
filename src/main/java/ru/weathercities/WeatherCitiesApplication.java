package ru.weathercities;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class WeatherCitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherCitiesApplication.class, args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC+3"));
	}

}
