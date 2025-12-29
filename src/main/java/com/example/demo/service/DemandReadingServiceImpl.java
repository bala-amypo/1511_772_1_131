package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.DemandReadingService;

import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository repo;
    private final ZoneRepository zoneRepo;

    public DemandReadingServiceImpl(DemandReadingRepository r, ZoneRepository z) {
        this.repo = r;
        this.zoneRepo = z;
    }

    public DemandReading createReading(DemandReading r) {
        Zone z = zoneRepo.findById(r.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (r.getDemandMW() < 0)
            throw new BadRequestException(">= 0");

        if (r.getRecordedAt().isAfter(Instant.now()))
            throw new BadRequestException("future");

        r.setZone(z);
        return repo.save(r);
    }

    public List<DemandReading> getReadingsForZone(Long zoneId) {
        zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return repo.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    public DemandReading getLatestReading(Long zoneId) {
        return repo.findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("No readings"));
    }

    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> list = getReadingsForZone(zoneId);
        return list.subList(0, Math.min(limit, list.size()));
    }
}
