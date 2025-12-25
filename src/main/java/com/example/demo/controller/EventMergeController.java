package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.EventMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/event-merge")
public class EventMergeController {
    
    @Autowired
    private EventMergeService eventMergeService;
    
    @PostMapping("/merge-all")
    public ResponseEntity<List<HarmonizedCalendar>> mergeAllCalendars() {
        return ResponseEntity.ok(eventMergeService.mergeAllCalendars());
    }
    
    @PostMapping("/merge/{eventId}")
    public ResponseEntity<HarmonizedCalendar> mergeEvent(
            @PathVariable Long eventId,
            @RequestParam String sourceType) {
        return ResponseEntity.ok(eventMergeService.mergeEvent(eventId, sourceType));
    }
    
    @GetMapping("/merged-events")
    public ResponseEntity<List<HarmonizedCalendar>> getMergedEvents() {
        return ResponseEntity.ok(eventMergeService.getMergedEvents());
    }
}