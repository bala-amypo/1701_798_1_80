// package com.example.demo.repository;

// import com.example.demo.entity.ClashRecord;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;
// import java.util.List;

// @Repository
// public interface ClashRecordRepository extends JpaRepository<ClashRecord, Long> {
    
//     @Query("SELECT c FROM ClashRecord c WHERE c.eventAId = :eventAId OR c.eventBId = :eventBId")
//     List<ClashRecord> findByEventAIdOrEventBId(@Param("eventAId") Long eventAId, @Param("eventBId") Long eventBId);
    
//     List<ClashRecord> findByResolvedFalse();
// }

package com.example.demo.repository;

import com.example.demo.entity.ClashRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClashRecordRepository extends JpaRepository<ClashRecord, Long> {
    
    List<ClashRecord> findByEventAIdOrEventBId(Long eventAId, Long eventBId);

    List<ClashRecord> findByResolvedFalse();
}
