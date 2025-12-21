package com.example.demo.controller;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restorations")
public class ZoneRestorationController {

    private final ZoneRestorationService zoneRestorationService;

    // Constructor injection
    public ZoneRestorationController(ZoneRestorationService zoneRestorationService) {
        this.zoneRestorationService = zoneRestorationService;
    }

    // Restore a zone
    @PostMapping
    public ZoneRestorationRecord restoreZone(
            @RequestParam Long zoneId,
            @RequestParam Long eventId,
            @RequestParam(required = false) String notes) {

        return zoneRestorationService.restoreZone(zoneId, eventId, notes);
    }

    // Get restoration record by ID
    @GetMapping("/{id}")
    public ZoneRestorationRecord getRecordById(@PathVariable Long id) {
        return zoneRestorationService.getRecordById(id);
    }

    // Get restoration records for a zone
    @GetMapping("/zone/{zoneId}")
    public List<ZoneRestorationRecord> getRecordsForZone(@PathVariable Long zoneId) {
        return zoneRestorationService.getRecordsForZone(zoneId);
    }
}
