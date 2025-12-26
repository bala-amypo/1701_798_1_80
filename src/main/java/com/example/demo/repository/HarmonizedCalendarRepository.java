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
    @Query("SELECT hc FROM HarmonizedCalendar hc WHERE hc.effectiveFrom <= :date AND hc.effectiveTo >= :date")
    List<HarmonizedCalendar> findByEffectiveFromLessThanEqualAndEffectiveToGreaterThanEqual(
            @Param("date") LocalDate date, @Param("date") LocalDate date2);
    
    @Query("SELECT hc FROM HarmonizedCalendar hc WHERE hc.effectiveFrom <= :end AND hc.effectiveTo >= :start")
    List<HarmonizedCalendar> findCalendarsWithinRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
}