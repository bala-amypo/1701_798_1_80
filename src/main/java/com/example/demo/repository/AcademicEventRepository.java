package com.example.demo.repository;

import com.example.demo.entity.AcademicEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AcademicEventRepository extends JpaRepository<AcademicEvent, Long> {
    List<AcademicEvent> findByBranchId(Long branchId);  // This is correct
    List<AcademicEvent> findByStartDateBetween(LocalDate start, LocalDate end);
    List<AcademicEvent> findByEventType(String eventType);
    List<AcademicEvent> findByBranchIdAndStartDateBetween(Long branchId, LocalDate start, LocalDate end);
}