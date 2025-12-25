package com.example.demo.repository;

import com.example.demo.entity.ClashRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClashRecordRepository extends JpaRepository<ClashRecord, Long> {
    
    // 添加这个方法
    List<ClashRecord> findByEventId1(Long eventId1);
    List<ClashRecord> findByEventId2(Long eventId2);
    
    List<ClashRecord> findByResolved(boolean resolved);
}