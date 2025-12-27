package com.example.demo.controller;

import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService service;

    public ZoneController(ZoneService service) {
        this.service = service;
    }

    @PostMapping
    public Zone create(@RequestBody Zone z) {
        return service.createZone(z);
    }

    @PutMapping("/{id}")
    public Zone update(@PathVariable Long id, @RequestBody Zone z) {
        return service.updateZone(id, z);
    }

    @GetMapping("/{id}")
    public Zone get(@PathVariable Long id) {
        return service.getZoneById(id);
    }

    @GetMapping
    public List<Zone> all() {
        return service.getAllZones();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateZone(id);
    }
}
