package com.oskarpolak.weatherapi.api.models;

import org.springframework.http.HttpStatus;

public class WeatherResponseDto {
    private String message;
    private HttpStatus httpStatus;
    private Object payload;

    private WeatherResponseDto(Builder builder) {
        this.message = builder.message;
        this.httpStatus = builder.httpStatus;
        this.payload = builder.payload;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object getPayload() {
        return payload;
    }

    public static class Builder{
        private String message;
        private HttpStatus httpStatus;
        private Object payload;

        public Builder(HttpStatus status){
            this.httpStatus = status;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder payload(Object payload){
            this.payload = payload;
            return this;
        }

        public WeatherResponseDto build(){
            return new WeatherResponseDto(this);
        }
    }
}
