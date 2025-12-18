// SupplyForecast Entity
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Entity
public class SupplyForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double availableSupplyMW;

    private Timestamp forecastStart;

    private Timestamp forecastEnd;

    private Timestamp generatedAt;
}
