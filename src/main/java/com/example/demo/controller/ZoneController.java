package com.example.demo.controller;

import com.example.demo.entity.Zone;
import com.example.demo.repository.ZoneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneRepository zoneRepository;

    public ZoneController(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }
}
