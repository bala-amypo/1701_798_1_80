package com.example.demo.repository;

import com.example.demo.entity.ClashRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClashRecordRepository extends JpaRepository<ClashRecord, Long> {
    @Query("SELECT c FROM ClashRecord c WHERE c.eventAId = :eventId OR c.eventBId = :eventId")
    List<ClashRecord> findByEventAIdOrEventBId(@Param("eventId") Long eventId);
    
    List<ClashRecord> findByResolvedFalse();
}