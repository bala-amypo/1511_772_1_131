// Zone Entity
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String zoneName;

    private Integer priorityLevel;

    private Integer population;

    private Boolean active = true;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
