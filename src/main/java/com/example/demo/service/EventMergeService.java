package com.example.demo.service;

import com.example.demo.entity.HarmonizedCalendar;
import java.util.List;

public interface EventMergeService {
    List<HarmonizedCalendar> mergeAllCalendars();
    HarmonizedCalendar mergeEvent(Long eventId, String sourceType);
    List<HarmonizedCalendar> getMergedEvents();
}