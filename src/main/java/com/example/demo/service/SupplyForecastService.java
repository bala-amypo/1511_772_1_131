package com.example.demo.service;

import com.example.demo.entity.SupplyForecast;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface SupplyForecastService {

    SupplyForecast createForecast(SupplyForecast forecast);

    SupplyForecast updateForecast(Long id, SupplyForecast forecast);

    SupplyForecast getLatestForecast();

    List<SupplyForecast> getAllForecasts();
}
