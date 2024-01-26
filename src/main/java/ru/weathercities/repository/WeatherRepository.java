package ru.weathercities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.weathercities.entity.Temperature;

public interface WeatherRepository extends JpaRepository<Temperature, Long> {

}
