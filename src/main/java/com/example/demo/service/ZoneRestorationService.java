package com.example.demo.service;

import com.example.demo.entity.ZoneRestorationRecord;

import java.util.List;

public interface ZoneRestorationService {

    ZoneRestorationRecord restoreZone(Long zoneId, Long eventId, String notes);

    ZoneRestorationRecord getRecordById(Long id);

    List<ZoneRestorationRecord> getRecordsForZone(Long zoneId);
}
