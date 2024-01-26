package ru.weathercities.repository;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.weathercities.entity.City;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String city);

    @Query("select new org.apache.commons.lang3.tuple.ImmutableTriple(c.name, t.localDate, t.temperature) " +
            "from City c inner join Temperature t on t.cityId = c.id " +
            "where c.name = :name " +
            "order by t.localDate, t.time desc ")
    Page<Triple<String, LocalDate, Double>> getWeatherLast(String name, Pageable pageable);

    @Query("select new org.apache.commons.lang3.tuple.ImmutableTriple(c.name, t.localDate, t.temperature) " +
            "from City c inner join Temperature t on t.cityId = c.id " +
            "where c.name = :name and t.localDate = :date")
    List<Triple<String, LocalDate, Double>> getWeatherWithDate(String name, LocalDate date);
}
