// ZoneRestorationRecord Entity
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Entity
public class ZoneRestorationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private Timestamp restoredAt;

    private Long eventId;

    private String notes;
}
