package com.oskarpolak.weatherapi.app.models.repository;

import com.oskarpolak.weatherapi.app.models.entities.LogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Integer> {
}
