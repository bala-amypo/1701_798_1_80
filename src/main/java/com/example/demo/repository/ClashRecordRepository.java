package com.example.demo.repository;

import com.example.demo.model.ClashRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClashRecordRepository extends JpaRepository<ClashRecord, Long> {
    
    List<ClashRecord> findByResolved(boolean resolved);
    
    List<ClashRecord> findByEventId1(Long eventId1);
    
    List<ClashRecord> findByEventId2(Long eventId2);
    
    List<ClashRecord> findByClashType(String clashType);
    
    List<ClashRecord> findBySeverity(String severity);
    
    List<ClashRecord> findByEventId1AndEventId2(Long eventId1, Long eventId2);
}