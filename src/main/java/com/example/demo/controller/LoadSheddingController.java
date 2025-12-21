package com.example.demo.controller;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.service.LoadSheddingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/load-shedding")
public class LoadSheddingController {

    private final LoadSheddingService loadSheddingService;
    private final LoadSheddingEventRepository loadSheddingEventRepository;

    public LoadSheddingController(LoadSheddingService loadSheddingService,
                                  LoadSheddingEventRepository loadSheddingEventRepository) {
        this.loadSheddingService = loadSheddingService;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
    }

    // Trigger load shedding using forecastId
    @PostMapping("/trigger/{forecastId}")
    public LoadSheddingEvent triggerLoadShedding(@PathVariable Long forecastId) {
        return loadSheddingService.triggerLoadShedding(forecastId);
    }

    // Get events by zone
    @GetMapping("/zone/{zoneId}")
    public List<LoadSheddingEvent> getEventsByZone(@PathVariable Long zoneId) {
        return loadSheddingEventRepository.findByZoneIdOrderByEventStartDesc(zoneId);
    }

    // Get event by ID
    @GetMapping("/{id}")
    public LoadSheddingEvent getEventById(@PathVariable Long id) {
        return loadSheddingEventRepository.findById(id).orElse(null);
    }

    // Get all events
    @GetMapping
    public List<LoadSheddingEvent> getAllEvents() {
        return loadSheddingEventRepository.findAll();
    }
}
