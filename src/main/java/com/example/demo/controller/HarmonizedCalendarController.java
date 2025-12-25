package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/harmonized-calendars")
public class HarmonizedCalendarController {
    
    @Autowired
    private HarmonizedCalendarService harmonizedCalendarService;
    
    @GetMapping
    public ResponseEntity<List<HarmonizedCalendar>> getAllHarmonizedCalendars() {
        return ResponseEntity.ok(harmonizedCalendarService.getAllHarmonizedCalendars());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> getHarmonizedCalendarById(@PathVariable Long id) {
        return harmonizedCalendarService.getHarmonizedCalendarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<HarmonizedCalendar> createHarmonizedCalendar(@RequestBody HarmonizedCalendar harmonizedCalendar) {
        return ResponseEntity.ok(harmonizedCalendarService.saveHarmonizedCalendar(harmonizedCalendar));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> updateHarmonizedCalendar(@PathVariable Long id, @RequestBody HarmonizedCalendar harmonizedCalendar) {
        HarmonizedCalendar updated = harmonizedCalendarService.updateHarmonizedCalendar(id, harmonizedCalendar);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarmonizedCalendar(@PathVariable Long id) {
        harmonizedCalendarService.deleteHarmonizedCalendar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<HarmonizedCalendar>> getEventsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(harmonizedCalendarService.getEventsByDateRange(startDate, endDate));
    }
    
    @GetMapping("/source/{sourceSystem}")
    public ResponseEntity<List<HarmonizedCalendar>> getEventsBySource(@PathVariable String sourceSystem) {
        return ResponseEntity.ok(harmonizedCalendarService.getEventsBySource(sourceSystem));
    }
    
    @GetMapping("/type/{eventType}")
    public ResponseEntity<List<HarmonizedCalendar>> getEventsByType(@PathVariable String eventType) {
        return ResponseEntity.ok(harmonizedCalendarService.getEventsByType(eventType));
    }
}