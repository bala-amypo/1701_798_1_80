package com.example.demo.repository;

import com.example.demo.entity.HarmonizedCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HarmonizedCalendarRepository extends JpaRepository<HarmonizedCalendar, Long> {
    
    List<HarmonizedCalendar> findByEffectiveFromBetween(LocalDate startDate, LocalDate endDate);
    
    List<HarmonizedCalendar> findByCalendarNameContaining(String name);
    
    List<HarmonizedCalendar> findByIsActive(Boolean isActive);
    
    List<HarmonizedCalendar> findByPriority(Integer priority);
}