// src/main/java/com/example/demo/controller/HarmonizedCalendarController.java
package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/harmonized-calendars")
@Tag(name = "Harmonized Calendars", description = "Harmonized calendar generation and retrieval endpoints")
public class HarmonizedCalendarController {
    
    private final HarmonizedCalendarService calendarService;
    
    public HarmonizedCalendarController(HarmonizedCalendarService calendarService) {
        this.calendarService = calendarService;
    }
    
    @PostMapping("/generate")
    @Operation(summary = "Generate a harmonized calendar", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<HarmonizedCalendar> generateHarmonizedCalendar(
            @RequestParam String title,
            @RequestParam String generatedBy) {
        HarmonizedCalendar calendar = calendarService.generateHarmonizedCalendar(title, generatedBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(calendar);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get calendar by ID", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<HarmonizedCalendar> getCalendarById(@PathVariable Long id) {
        HarmonizedCalendar calendar = calendarService.getCalendarById(id);
        return ResponseEntity.ok(calendar);
    }
    
    @GetMapping
    @Operation(summary = "List all calendars", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<HarmonizedCalendar>> getAllCalendars() {
        List<HarmonizedCalendar> calendars = calendarService.getAllCalendars();
        return ResponseEntity.ok(calendars);
    }
    
    @GetMapping("/range")
    @Operation(summary = "Get calendars within date range", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<HarmonizedCalendar>> getCalendarsWithinRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<HarmonizedCalendar> calendars = calendarService.getCalendarsWithinRange(start, end);
        return ResponseEntity.ok(calendars);
    }
}