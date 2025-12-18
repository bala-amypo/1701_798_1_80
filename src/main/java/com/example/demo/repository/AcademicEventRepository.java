package com.example.demo.repository;

import com.example.demo.entity.AcademicEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AcademicEventRepository extends JpaRepository<AcademicEvent, Long> {
    
    @Query("SELECT e FROM AcademicEvent e WHERE e.userAccount.id = :userId " +
           "AND DATE(e.startTime) BETWEEN :startDate AND :endDate")
    List<AcademicEvent> findByUserAccountIdAndDateRange(@Param("userId") Long userId, 
                                                       @Param("startDate") LocalDate startDate, 
                                                       @Param("endDate") LocalDate endDate);
}