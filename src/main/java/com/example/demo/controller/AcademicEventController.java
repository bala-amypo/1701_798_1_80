package com.example.demo.controller;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.service.AcademicEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Academic Events", description = "Academic event management APIs")
public class AcademicEventController {
    
    @Autowired
    private AcademicEventService academicEventService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER')")
    @Operation(summary = "Create new academic event")
    public ResponseEntity<AcademicEvent> createEvent(@RequestBody AcademicEvent event) {
        AcademicEvent created = academicEventService.createEvent(event);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER')")
    @Operation(summary = "Update event")
    public ResponseEntity<AcademicEvent> updateEvent(@PathVariable Long id, @RequestBody AcademicEvent event) {
        AcademicEvent updated = academicEventService.updateEvent(id, event);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/branch/{branchId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
    @Operation(summary = "Get events by branch")
    public ResponseEntity<List<AcademicEvent>> getEventsByBranch(@PathVariable Long branchId) {
        List<AcademicEvent> events = academicEventService.getEventsByBranch(branchId);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
    @Operation(summary = "Get event by ID")
    public ResponseEntity<AcademicEvent> getEventById(@PathVariable Long id) {
        AcademicEvent event = academicEventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
    @Operation(summary = "List all events")
    public ResponseEntity<List<AcademicEvent>> getAllEvents() {
        List<AcademicEvent> events = academicEventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}