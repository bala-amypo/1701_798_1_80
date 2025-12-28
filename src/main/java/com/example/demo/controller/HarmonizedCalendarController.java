package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/harmonized-calendar")
@SecurityRequirement(name = "bearerAuth")
public class HarmonizedCalendarController {

    private final HarmonizedCalendarService calendarService;

    public HarmonizedCalendarController(HarmonizedCalendarService calendarService) {
        this.calendarService = calendarService;
    }
    
    @PostMapping("/generate/{title}/{by}")
    public HarmonizedCalendar generateCalendar(@PathVariable String title, @PathVariable String by) {
        return calendarService.generateHarmonizedCalendar(title, by);
    }
    // @PostMapping
    // public ResponseEntity<HarmonizedCalendar> generateCalendar(
    //         @RequestParam String title,
    //         @RequestParam String generatedBy) {

    //     return ResponseEntity.ok(
    //             calendarService.generateCalendar(title, generatedBy)
    //     );
    // }

    @GetMapping
    public ResponseEntity<List<HarmonizedCalendar>> getAllCalendars() {
        return ResponseEntity.ok(calendarService.getAllCalendars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> getCalendar(@PathVariable Long id) {
        return ResponseEntity.ok(calendarService.getCalendar(id));
    }
}
