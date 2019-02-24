package com.oskarpolak.weatherapi.app.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@Data
public class LogEntity {
    private @Id @GeneratedValue int id;
    private @Column(name = "city_name") String cityName;
    private double temp;
    private @Column(name = "request_date") LocalDateTime requestDate;


    public LogEntity(String cityName, double temp) {
        this.cityName = cityName;
        this.temp = temp;
    }

    public LogEntity() { }
}
