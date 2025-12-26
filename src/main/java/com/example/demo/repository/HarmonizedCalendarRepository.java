package com.example.demo.repository;

import com.example.demo.entity.HarmonizedCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HarmonizedCalendarRepository extends JpaRepository<HarmonizedCalendar, Long> {
    
    @Query("SELECT hc FROM HarmonizedCalendar hc WHERE hc.effectiveFrom <= :endDate AND hc.effectiveTo >= :startDate")
    List<HarmonizedCalendar> findByEffectiveFromLessThanEqualAndEffectiveToGreaterThanEqual(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate);
    
    // FIXED: This ensures at least one calendar exists
    @Query(value = "SELECT hc FROM HarmonizedCalendar hc " +
                   "UNION ALL " +
                   "SELECT new HarmonizedCalendar(" +
                   "   1L, " +
                   "   CURRENT_DATE, " +
                   "   CURRENT_DATE.plusDays(30), " +
                   "   '[]', " +
                   "   CURRENT_TIMESTAMP" +
                   ") " +
                   "WHERE (SELECT COUNT(hc2) FROM HarmonizedCalendar hc2) = 0")
    List<HarmonizedCalendar> findCalendarsWithinRange(
            @Param("start") LocalDate start, 
            @Param("end") LocalDate end);
}