package com.oskarpolak.weatherapi.api.controllers;

import com.oskarpolak.weatherapi.api.models.LogDto;
import com.oskarpolak.weatherapi.api.models.WeatherResponseDto;
import com.oskarpolak.weatherapi.app.models.LogService;
import com.oskarpolak.weatherapi.app.models.entities.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/log")
public class LogApiController {

    final LogService logService;

    @Autowired
    public LogApiController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") int id){
        Optional<LogEntity> logEntity = logService.findById(id);

        HttpStatus httpStatus = logEntity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(httpStatus)
                .body(new WeatherResponseDto.Builder(httpStatus)
                .message(logEntity.isPresent() ? "OK" : "Log with this id not exist")
                .payload(logEntity.isPresent() ? logEntity.get() : null)
                .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id){
        logService.deleteByIdAndCheck(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WeatherResponseDto.Builder(HttpStatus.OK)
                .payload(null)
                .message("Log was deleted correctly")
                .build());
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity saveNewLog(@RequestBody /*@Valid*/ LogDto logDto) {
        logService.saveLog(logDto.getCity(), logDto.getTemp());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WeatherResponseDto.Builder(HttpStatus.OK)
                        .payload(null)
                        .message("Log was created correctly")
                        .build());
    }

    @PutMapping(value = "", consumes = "application/json")
    public ResponseEntity editLog(@RequestBody @Valid LogDto logDto,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        logService.editLog(logDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WeatherResponseDto.Builder(HttpStatus.OK)
                        .payload(null)
                        .message("Log with id " + logDto.getId() + " edited correctly")
                        .build());
    }


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity accessException(DataAccessException ex){
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(new WeatherResponseDto.Builder(HttpStatus.PRECONDITION_FAILED)
                        .payload(null)
                        .message(ex.getMessage())
                        .build());
    }

}
