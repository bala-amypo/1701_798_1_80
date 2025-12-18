// src/main/java/com/example/demo/service/HarmonizedCalendarService.java
package com.example.demo.service;

import com.example.demo.entity.HarmonizedCalendar;
import java.time.LocalDate;
import java.util.List;

public interface HarmonizedCalendarService {
    HarmonizedCalendar generateHarmonizedCalendar(String title, String generatedBy);
    HarmonizedCalendar getCalendarById(Long id);
    List<HarmonizedCalendar> getAll