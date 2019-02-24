package com.oskarpolak.weatherapi.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastWeatherDto {
    List<WeatherDto> list;
}
