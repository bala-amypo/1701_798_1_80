// package com.example.demo.repository;

// import com.example.demo.entity.EventMergeRecord;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface EventMergeRecordRepository extends JpaRepository<EventMergeRecord, Long> {
//     List<EventMergeRecord> findByMergedStartDateBetween(LocalDate start, LocalDate end);
// }

package com.example.demo.repository;

import com.example.demo.entity.EventMergeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventMergeRecordRepository extends JpaRepository<EventMergeRecord, Long> {

    List<EventMergeRecord> findByMergedStartDateBetween(LocalDate startDate, LocalDate endDate);
}
