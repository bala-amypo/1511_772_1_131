package com.example.demo.service.impl;

import com.example.demo.entity.LoadSheddingEvent;
import com.example.demo.entity.Zone;
import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.LoadSheddingEventRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.repository.ZoneRestorationRecordRepository;
import com.example.demo.service.ZoneRestorationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository zoneRestorationRecordRepository;
    private final LoadSheddingEventRepository loadSheddingEventRepository;
    private final ZoneRepository zoneRepository;

    // ⚠️ Constructor order MUST match spec
    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository zoneRestorationRecordRepository,
            LoadSheddingEventRepository loadSheddingEventRepository,
            ZoneRepository zoneRepository) {

        this.zoneRestorationRecordRepository = zoneRestorationRecordRepository;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public ZoneRestorationRecord restoreZone(Long zoneId, Long eventId, String notes) {

        Zone zone = zoneRepository.findById(zoneId).orElseThrow();
        LoadSheddingEvent event = loadSheddingEventRepository.findById(eventId).orElseThrow();

        zone.setActive(true);
        zoneRepository.save(zone);

        ZoneRestorationRecord record = ZoneRestorationRecord.builder()
                .zone(zone)
                .eventId(eventId)
                .restoredAt(Instant.now())
                .notes(notes)
                .build();

        return zoneRestorationRecordRepository.save(record);
    }

    @Override
    public ZoneRestorationRecord getRecordById(Long id) {
        return zoneRestorationRecordRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return zoneRestorationRecordRepository
                .findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
