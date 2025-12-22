package com.example.demo.service.impl;

import com.example.demo.entity.DemandReading;
import com.example.demo.entity.Zone;
import com.example.demo.repository.DemandReadingRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.DemandReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository demandReadingRepository;
    private final ZoneRepository zoneRepository;

    // Constructor Injection (ORDER IS IMPORTANT)
    public DemandReadingServiceImpl(DemandReadingRepository demandReadingRepository,
                                    ZoneRepository zoneRepository) {
        this.demandReadingRepository = demandReadingRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public DemandReading createReading(DemandReading reading) {
        Zone zone = zoneRepository.findById(reading.getZone().getId()).orElseThrow();
        reading.setZone(zone);
        return demandReadingRepository.save(reading);
    }

    @Override
    public List<DemandReading> getReadingsForZone(Long zoneId) {
        return demandReadingRepository.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    @Override
    public DemandReading getLatestReading(Long zoneId) {
        return demandReadingRepository
                .findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElse(null);
    }

    @Override
    public List<DemandReading> getRecentReadings(Long zoneId) {
        return getReadingsForZone(zoneId);
    }
}
