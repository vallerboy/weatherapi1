package com.oskarpolak.weatherapi.app.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CheckWeatherJob {

    final WeatherService weatherService;
    final SmsService smsService;
    final static String CITY_NAME = "Kraków";
    final static String TEL_NUMBER = "788848839";

    @Autowired
    public CheckWeatherJob(WeatherService weatherService, SmsService smsService) {
        this.weatherService = weatherService;
        this.smsService = smsService;
    }

    //@Scheduled(fixedRate = 1000 * 60 * 2)
    public void checkWeather() {
        WeatherDto weather = weatherService.getWeather(CITY_NAME);
        smsService.sendSms(
                    "Aktualna temp to: " + weather.getBaseWeatherParameters().getTemp(),
                            TEL_NUMBER);
    }

}
