package com.example.demo.repository;

import com.example.demo.entity.ClashRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClashRecordRepository extends JpaRepository<ClashRecord, Long> {
    List<ClashRecord> findByStatus(String status);
    List<ClashRecord> findByClashType(String clashType);
    List<ClashRecord> findByEvent1IdOrEvent2Id(Long event1Id, Long event2Id);
    List<ClashRecord> findByResolved(boolean resolved); // Add this method
}   