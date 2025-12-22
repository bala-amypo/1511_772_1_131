package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository supplyForecastRepository;
    private final ZoneRepository zoneRepository;
    private final DemandReadingRepository demandReadingRepository;
    private final LoadSheddingEventRepository loadSheddingEventRepository;

    // ⚠️ Constructor order MUST match spec
    public LoadSheddingServiceImpl(SupplyForecastRepository supplyForecastRepository,
                                   ZoneRepository zoneRepository,
                                   DemandReadingRepository demandReadingRepository,
                                   LoadSheddingEventRepository loadSheddingEventRepository) {
        this.supplyForecastRepository = supplyForecastRepository;
        this.zoneRepository = zoneRepository;
        this.demandReadingRepository = demandReadingRepository;
        this.loadSheddingEventRepository = loadSheddingEventRepository;
    }

    @Override
    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast = supplyForecastRepository.findById(forecastId).orElseThrow();

        // Get active zones ordered by lowest priority first
        List<Zone> zones = zoneRepository.findByActiveTrueOrderByPriorityLevelAsc();

        double totalDemand = 0.0;

        for (Zone zone : zones) {
            DemandReading latest =
                    demandReadingRepository
                            .findFirstByZoneIdOrderByRecordedAtDesc(zone.getId())
                            .orElse(null);

            if (latest != null) {
                totalDemand += latest.getDemandMW();
            }
        }

        // If no overload, do nothing
        if (totalDemand <= forecast.getAvailableSupplyMW()) {
            return null;
        }

        // Shed load from lowest priority zones
        for (Zone zone : zones) {

            DemandReading latest =
                    demandReadingRepository
                            .findFirstByZoneIdOrderByRecordedAtDesc(zone.getId())
                            .orElse(null);

            if (latest == null) continue;

            LoadSheddingEvent event = LoadSheddingEvent.builder()
                    .zone(zone)
                    .eventStart(Instant.now())
                    .reason("Demand exceeds supply")
                    .triggeredByForecastId(forecastId)
                    .expectedDemandReductionMW(latest.getDemandMW())
                    .build();

            zone.setActive(false);
            zoneRepository.save(zone);

            loadSheddingEventRepository.save(event);

            totalDemand -= latest.getDemandMW();

            if (totalDemand <= forecast.getAvailableSupplyMW()) {
                return event;
            }
        }

        return null;
    }
}
