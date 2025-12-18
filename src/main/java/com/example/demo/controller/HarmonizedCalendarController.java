package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class HarmonizedCalendarController {

    @Autowired
    private HarmonizedCalendarService calendarService;

    @PostMapping("/generate")
    public ResponseEntity<HarmonizedCalendar> generateHarmonizedCalendar(
            @RequestParam String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam String timeZone) {
        
        HarmonizedCalendar calendar = calendarService.generateHarmonizedCalendar(
            userId, startDate, endDate, timeZone
        );
        return ResponseEntity.ok(calendar);
    }

    @GetMapping("/range")
    public ResponseEntity<List<HarmonizedCalendar>> getCalendarsWithinRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        List<HarmonizedCalendar> calendars = calendarService.getCalendarsWithinRange(startDate, endDate);
        return ResponseEntity.ok(calendars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> getHarmonizedCalendarById(@PathVariable String id) {
        HarmonizedCalendar calendar = calendarService.getHarmonizedCalendarById(id);
        return ResponseEntity.ok(calendar);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HarmonizedCalendar>> getHarmonizedCalendarsByUserId(@PathVariable String userId) {
        List<HarmonizedCalendar> calendars = calendarService.getHarmonizedCalendarsByUserId(userId);
        return ResponseEntity.ok(calendars);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarmonizedCalendar(@PathVariable String id) {
        calendarService.deleteHarmonizedCalendar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> updateHarmonizedCalendar(
            @PathVariable String id,
            @RequestBody HarmonizedCalendar updatedCalendar) {
        
        HarmonizedCalendar calendar = calendarService.updateHarmonizedCalendar(id, updatedCalendar);
        return ResponseEntity.ok(calendar);
    }
}