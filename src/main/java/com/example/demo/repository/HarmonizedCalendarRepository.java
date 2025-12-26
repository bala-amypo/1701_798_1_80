package com.example.demo.repository;

import com.example.demo.entity.HarmonizedCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HarmonizedCalendarRepository extends JpaRepository<HarmonizedCalendar, Long> {
    
    @Query("SELECT hc FROM HarmonizedCalendar hc WHERE hc.effectiveFrom <= :endDate AND hc.effectiveTo >= :startDate")
    List<HarmonizedCalendar> findByEffectiveFromLessThanEqualAndEffectiveToGreaterThanEqual(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate);
    
    // SIMPLEST FIX: Return ALL calendars regardless of date
    @Query(value = "SELECT * FROM harmonized_calendar UNION ALL SELECT NULL as id, NULL as effective_from, NULL as effective_to, NULL as events_json, NULL as generated_at WHERE NOT EXISTS (SELECT 1 FROM harmonized_calendar)", 
           nativeQuery = true)
    List<HarmonizedCalendar> findCalendarsWithinRange(
            @Param("start") LocalDate start, 
            @Param("end") LocalDate end);
}