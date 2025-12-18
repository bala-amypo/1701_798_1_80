// src/main/java/com/example/demo/controller/AcademicEventController.java
package com.example.demo.controller;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.service.AcademicEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Academic Events", description = "Academic event management endpoints")
public class AcademicEventController {
    
    private final AcademicEventService eventService;
    
    public AcademicEventController(AcademicEventService eventService) {
        this.eventService = eventService;
    }
    
    @PostMapping
    @Operation(summary = "Create new academic event", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<AcademicEvent> createEvent(@Valid @RequestBody AcademicEvent event) {
        AcademicEvent createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update event", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<AcademicEvent> updateEvent(
            @PathVariable Long id, 
            @Valid @RequestBody AcademicEvent event) {
        AcademicEvent updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }
    
    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Get events by branch", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<AcademicEvent>> getEventsByBranch(@PathVariable Long branchId) {
        List<AcademicEvent> events = eventService.getEventsByBranch(branchId);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<AcademicEvent> getEventById(@PathVariable Long id) {
        AcademicEvent event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    @GetMapping
    @Operation(summary = "List all events", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<AcademicEvent>> getAllEvents() {
        List<AcademicEvent> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}