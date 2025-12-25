package com.example.demo.repository;

import com.example.demo.entity.HarmonizedCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarmonizedCalendarRepository extends JpaRepository<HarmonizedCalendar, Long> {
    // 暂时移除或注释掉有问题的方法
    // List<HarmonizedCalendar> findByEffectiveFromBetween(LocalDate startDate, LocalDate endDate);
}