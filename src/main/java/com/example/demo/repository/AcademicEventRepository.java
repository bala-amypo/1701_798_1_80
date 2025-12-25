package com.example.demo.repository;

import com.example.demo.entity.AcademicEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AcademicEventRepository extends JpaRepository<AcademicEvent, Long> {
    List<AcademicEvent> findByCourseCode(String courseCode);
    List<AcademicEvent> findByEventType(String eventType);
    List<AcademicEvent> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<AcademicEvent> findByLocation(String location);
    List<AcademicEvent> findByTitleContaining(String title);
}