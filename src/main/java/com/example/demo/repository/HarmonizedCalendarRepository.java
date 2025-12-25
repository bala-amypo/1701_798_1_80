package com.example.demo.repository;

import com.example.demo.entity.HarmonizedCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HarmonizedCalendarRepository extends JpaRepository<HarmonizedCalendar, Long> {
    
    // 如果实体类中没有 effectiveFrom 字段，可以：
    // 1. 添加这个字段到实体类（推荐）
    // 2. 或者修改方法名以匹配现有字段
    
    List<HarmonizedCalendar> findByEffectiveFromBetween(LocalDate startDate, LocalDate endDate);
    
    // 其他可能的查询方法
    List<HarmonizedCalendar> findByCalendarNameContaining(String name);
    
    List<HarmonizedCalendar> findByIsActive(Boolean isActive);
    
    List<HarmonizedCalendar> findByPriority(Integer priority);
}