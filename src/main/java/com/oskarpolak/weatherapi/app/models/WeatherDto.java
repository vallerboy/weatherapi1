package com.oskarpolak.weatherapi.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    @JsonProperty("main")
    private WeatherTemp baseWeatherParameters;
    private WeatherClouds clouds;

    @JsonProperty("dt_txt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //2019-02-23 15:00:00
    private LocalDateTime date;

    @Data
    public static class WeatherTemp {
        private double temp;
    }

    @Data
    public static class WeatherClouds {
        private int all;
    }
}
