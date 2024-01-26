package ru.weathercities.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@Table(name = "temperature", schema = "weathercities")
public class Temperature extends BaseEntity<Long> {

    @Column(name = "CITY_ID")
    private Long cityId;
    @Column(name = "LOCALDATE")
    private LocalDate localDate;
    @Column(name = "TEMPERATURE")
    private Double temperature;
    @Column(name = "TIME")
    private LocalTime time;

    @Override
    public String toString() {
        return "Temperature{" +
                "cityId=" + cityId +
                ", localDate=" + localDate +
                ", temperature=" + temperature +
                ", time=" + time +
                '}';
    }
}
