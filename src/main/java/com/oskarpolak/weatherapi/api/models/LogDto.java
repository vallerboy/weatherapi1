package com.oskarpolak.weatherapi.api.models;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class LogDto {
    @Min(1)
    private int id;
    private String city;
    private double temp;
}
