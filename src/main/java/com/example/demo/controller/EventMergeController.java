package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.EventMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/merge")
public class EventMergeController {
    
    @Autowired
    private EventMergeService mergeService;
    
    @PostMapping("/merge-events")
    public ResponseEntity<List<HarmonizedCalendar>> mergeEvents() {
        List<HarmonizedCalendar> mergedCalendars = mergeService.mergeEvents();
        return ResponseEntity.ok(mergedCalendars);
    }
    
    @PostMapping("/merge-by-date")
    public ResponseEntity<List<HarmonizedCalendar>> mergeEventsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<HarmonizedCalendar> mergedCalendars = mergeService.mergeEventsByDateRange(startDate, endDate);
        return ResponseEntity.ok(mergedCalendars);
    }
    
    @GetMapping("/records")
    public ResponseEntity<List<HarmonizedCalendar>> getMergeRecordsByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<HarmonizedCalendar> records = mergeService.getMergeRecordsByDate(startDate, endDate);
        return ResponseEntity.ok(records);
    }
}