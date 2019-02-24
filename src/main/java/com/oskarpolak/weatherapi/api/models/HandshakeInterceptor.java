package com.oskarpolak.weatherapi.api.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class HandshakeInterceptor extends HandlerInterceptorAdapter implements WebMvcConfigurer {

    @Value("${our.api.key}")
    String apiKey;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().startsWith("/api/")){
            String apiKeyHeader = request.getHeader("api-key");
            if(apiKeyHeader == null || !apiKeyHeader.equals(apiKey)){
                response.sendError(403);
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }
}
