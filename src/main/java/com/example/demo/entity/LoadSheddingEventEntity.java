// LoadSheddingEvent Entity
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Entity
public class LoadSheddingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private Timestamp eventStart;

    private Timestamp eventEnd;

    private String reason;

    private Long triggeredByForecastId;

    private Double expectedDemandReductionMW;
}
