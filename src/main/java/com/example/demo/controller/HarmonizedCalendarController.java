package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendars")
public class HarmonizedCalendarController {
    
    @Autowired
    private HarmonizedCalendarService calendarService;
    
    @PostMapping
    public ResponseEntity<HarmonizedCalendar> createCalendar(@RequestBody HarmonizedCalendar calendar) {
        HarmonizedCalendar createdCalendar = calendarService.createCalendar(calendar);
        return ResponseEntity.ok(createdCalendar);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> getCalendarById(@PathVariable Long id) {
        HarmonizedCalendar calendar = calendarService.getCalendarById(id);
        if (calendar != null) {
            return ResponseEntity.ok(calendar);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<HarmonizedCalendar>> getAllCalendars() {
        List<HarmonizedCalendar> calendars = calendarService.getAllCalendars();
        return ResponseEntity.ok(calendars);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> updateCalendar(@PathVariable Long id, @RequestBody HarmonizedCalendar calendarDetails) {
        HarmonizedCalendar updatedCalendar = calendarService.updateCalendar(id, calendarDetails);
        if (updatedCalendar != null) {
            return ResponseEntity.ok(updatedCalendar);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long id) {
        boolean deleted = calendarService.deleteCalendar(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/range")
    public ResponseEntity<List<HarmonizedCalendar>> getCalendarsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<HarmonizedCalendar> calendars = calendarService.getCalendarsByDateRange(startDate, endDate);
        return ResponseEntity.ok(calendars);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<HarmonizedCalendar>> searchCalendarsByName(@RequestParam String name) {
        List<HarmonizedCalendar> calendars = calendarService.searchCalendarsByName(name);
        return ResponseEntity.ok(calendars);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<HarmonizedCalendar>> getActiveCalendars() {
        List<HarmonizedCalendar> calendars = calendarService.getActiveCalendars();
        return ResponseEntity.ok(calendars);
    }
    
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<HarmonizedCalendar>> getCalendarsByPriority(@PathVariable Integer priority) {
        List<HarmonizedCalendar> calendars = calendarService.getCalendarsByPriority(priority);
        return ResponseEntity.ok(calendars);
    }
}