package com.oskarpolak.weatherapi.app.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    String apiKey;

    final LogService logService;

    @Autowired
    public WeatherService(LogService logService) {
        this.logService = logService;
    }

    public WeatherDto getWeather(String cityName) {
        WeatherDto weatherDto = getRestTemplate().getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric", WeatherDto.class);

        logService.saveLog(cityName, weatherDto.getBaseWeatherParameters().getTemp());
        return weatherDto;
    }

    public ForecastWeatherDto getWeatherForecast(String cityName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("api-key", "klucz :)");

        HttpEntity<ForecastWeatherDto> forecastWeatherDtoHttpEntity = new HttpEntity<>(httpHeaders);

        return getRestTemplate().exchange("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey + "&units=metric", HttpMethod.GET, forecastWeatherDtoHttpEntity, ForecastWeatherDto.class).getBody();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
