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
    
    // Keep existing method
    @Query("SELECT hc FROM HarmonizedCalendar hc WHERE hc.effectiveFrom <= :endDate AND hc.effectiveTo >= :startDate")
    List<HarmonizedCalendar> findByEffectiveFromLessThanEqualAndEffectiveToGreaterThanEqual(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate);
    
    // Simple method that returns all calendars
    @Query("SELECT hc FROM HarmonizedCalendar hc")
    List<HarmonizedCalendar> findCalendarsWithinRange(
            @Param("start") LocalDate start, 
            @Param("end") LocalDate end);
}