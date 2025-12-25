package com.example.demo.service;

import com.example.demo.entity.HarmonizedCalendar;
import java.time.LocalDate;
import java.util.List;

public interface EventMergeService {
    List<HarmonizedCalendar> mergeEvents();
    List<HarmonizedCalendar> mergeEventsByDateRange(LocalDate startDate, LocalDate endDate);
    List<HarmonizedCalendar> getMergeRecordsByDate(LocalDate startDate, LocalDate endDate);
}