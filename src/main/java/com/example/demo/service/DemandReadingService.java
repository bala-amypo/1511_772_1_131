package com.example.demo.service;

import com.example.demo.entity.DemandReading;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DemandReadingService {

    DemandReading createReading(DemandReading reading);

    List<DemandReading> getReadingsForZone(Long zoneId);

    DemandReading getLatestReading(Long zoneId);

    List<DemandReading> getRecentReadings(Long zoneId, int limit);
}
