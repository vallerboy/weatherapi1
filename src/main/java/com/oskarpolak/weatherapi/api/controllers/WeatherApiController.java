package com.oskarpolak.weatherapi.api.controllers;


import com.oskarpolak.weatherapi.api.models.WeatherResponseDto;
import com.oskarpolak.weatherapi.app.models.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {
    final WeatherService weatherService;

    @Value("${our.api.key}")
    String ourApiKey;

    @Autowired
    public WeatherApiController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{cityName}")
    public ResponseEntity showWeatherForCity(@PathVariable("cityName") String city,
                                             @RequestHeader("api-key") String apiKey,
                                             HttpServletRequest httpServletRequest){


        if(apiKey != null && !apiKey.equals(ourApiKey)){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new WeatherResponseDto.Builder(HttpStatus.FORBIDDEN)
                    .message("Missing or bad api-key header")
                    .build());
        }
        WeatherResponseDto weatherResponseDto = new WeatherResponseDto.Builder(HttpStatus.OK)
                                                .message(city)
                                                .payload(weatherService.getWeather(city))
                                                .build();
        return ResponseEntity.ok(weatherResponseDto);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity catchHttpStatusCode(HttpStatusCodeException ex){
        return ResponseEntity
                .status(ex.getStatusCode()) //status odpowiedzi http naszego serwera
                .body(new WeatherResponseDto.Builder(ex.getStatusCode()) // zapis statusu do jsona dla body
                .message("Something go wrong, look at http response code") // zapis wiadomosci dla jsona dla body
                .payload(null) // zapis (tylko informacyjny) dla jsona
                .build()); // zbuduj obiket typu WeatherResponseDto.
    }
}
