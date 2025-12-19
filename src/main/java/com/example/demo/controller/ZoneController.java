package com.example.demo.controller;

import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    // CREATE ZONE
    @PostMapping
    public Zone createZone(@RequestBody Zone zone) {
        return zoneService.createZone(zone);
    }

    // UPDATE ZONE
    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        return zoneService.updateZone(id, zone);
    }

    // GET ZONE BY ID
    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id);
    }

    // GET ALL ZONES
    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    // DEACTIVATE ZONE
    @PutMapping("/{id}/deactivate")
    public String deactivateZone(@PathVariable Long id) {
        zoneService.deactivateZone(id);
        return "Zone deactivated successfully";
    }
}
