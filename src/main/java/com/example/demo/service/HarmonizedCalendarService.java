package com.example.demo.service;

import com.example.demo.entity.HarmonizedCalendar;
import java.time.LocalDate;
import java.util.List;

public interface HarmonizedCalendarService {
    HarmonizedCalendar createCalendar(HarmonizedCalendar calendar);
    HarmonizedCalendar getCalendarById(Long id);
    List<HarmonizedCalendar> getAllCalendars();
    HarmonizedCalendar updateCalendar(Long id, HarmonizedCalendar calendarDetails);
    boolean deleteCalendar(Long id);
    List<HarmonizedCalendar> getCalendarsByDateRange(LocalDate startDate, LocalDate endDate);
    List<HarmonizedCalendar> searchCalendarsByName(String name);
    List<HarmonizedCalendar> getActiveCalendars();
    List<HarmonizedCalendar> getCalendarsByPriority(Integer priority);
}