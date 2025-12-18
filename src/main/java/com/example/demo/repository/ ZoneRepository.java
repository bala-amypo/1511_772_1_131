// ZoneRepository
package com.example.demo.repository;

import com.example.demo.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByZoneName(String name);
    List<Zone> findByActiveTrueOrderByPriorityLevelAsc();
}
