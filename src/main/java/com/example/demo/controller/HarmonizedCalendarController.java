package com.example.demo.Controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/harmonized-calendars")
public class HarmonizedCalendarController {
    
    private final HarmonizedCalendarService calendarService;
    
    public HarmonizedCalendarController(HarmonizedCalendarService calendarService) {
        this.calendarService = calendarService;
    }
    
    @PostMapping("/generate")
    public ResponseEntity<HarmonizedCalendar> generateHarmonizedCalendar(
            @RequestParam String title,
            @RequestParam String generatedBy) {
        HarmonizedCalendar calendar = calendarService.generateHarmonizedCalendar(title, generatedBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(calendar);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> getCalendarById(@PathVariable Long id) {
        HarmonizedCalendar calendar = calendarService.getCalendarById(id);
        return ResponseEntity.ok(calendar);
    }
    
    @GetMapping
    public ResponseEntity<List<HarmonizedCalendar>> getAllCalendars() {
        List<HarmonizedCalendar> calendars = calendarService.getAllCalendars();
        return ResponseEntity.ok(calendars);
    }
    
    @GetMapping("/range")
    public ResponseEntity<List<HarmonizedCalendar>> getCalendarsWithinRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<HarmonizedCalendar> calendars = calendarService.getCalendarsWithinRange(start, end);
        return ResponseEntity.ok(calendars);
    }
}