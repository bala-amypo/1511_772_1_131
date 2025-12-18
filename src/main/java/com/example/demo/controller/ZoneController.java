// ZoneController
package com.example.demo.controller;

import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/")
    public Zone createZone(@RequestBody Zone zone) {
        return zoneService.createZone(zone);
    }

    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        return zoneService.updateZone(id, zone);
    }

    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id);
    }

    @GetMapping("/")
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @PutMapping("/{id}/deactivate")
    public Zone deactivateZone(@PathVariable Long id) {
        return zoneService.deactivateZone(id);
    }
}
