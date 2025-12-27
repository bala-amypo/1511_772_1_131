package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.service.SupplyForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supply-forecasts")
public class SupplyForecastController {

    private final SupplyForecastService service;

    public SupplyForecastController(SupplyForecastService service) {
        this.service = service;
    }

    @PostMapping
    public SupplyForecast create(@RequestBody SupplyForecast forecast) {
        return service.createForecast(forecast);
    }

    @PutMapping("/{id}")
    public SupplyForecast update(
            @PathVariable Long id,
            @RequestBody SupplyForecast forecast) {
        return service.updateForecast(id, forecast);
    }

    @GetMapping("/latest")
    public SupplyForecast getLatest() {
        return service.getLatestForecast();
    }

    @GetMapping
    public List<SupplyForecast> getAll() {
        return service.getAllForecasts();
    }
}
