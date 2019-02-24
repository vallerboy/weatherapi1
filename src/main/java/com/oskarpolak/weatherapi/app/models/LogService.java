package com.oskarpolak.weatherapi.app.models;


import com.oskarpolak.weatherapi.app.models.entities.LogEntity;
import com.oskarpolak.weatherapi.app.models.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    public void saveLog(String cityName, double temp){
       logRepository.save(new LogEntity(cityName, temp));
    }



}
