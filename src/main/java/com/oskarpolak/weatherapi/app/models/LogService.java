package com.oskarpolak.weatherapi.app.models;


import com.oskarpolak.weatherapi.api.models.LogDto;
import com.oskarpolak.weatherapi.app.models.entities.LogEntity;
import com.oskarpolak.weatherapi.app.models.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@EnableAsync
public class LogService {

    final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Async
    public void saveLog(String cityName, double temp){
       logRepository.save(new LogEntity(cityName, temp));
    }

    public Optional<LogEntity> findById(int id){
        return logRepository.findById(id);
    }



    public void deleteByIdAndCheck(int id){
        logRepository.deleteById(id);
    }


    public void editLog(LogDto logDto) {
        LogEntity logEntity = new LogEntity(logDto.getCity(), logDto.getTemp());
        logEntity.setId(logDto.getId());

        logRepository.save(logEntity);
    }
}
