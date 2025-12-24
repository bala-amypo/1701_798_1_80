package com.example.demo.Controller;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.service.AcademicEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AcademicEventController {
    
    private final AcademicEventService eventService;
    
    public AcademicEventController(AcademicEventService eventService) {
        this.eventService = eventService;
    }
    
    @PostMapping
    public ResponseEntity<AcademicEvent> createEvent(@RequestBody AcademicEvent event) {
        AcademicEvent createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AcademicEvent> updateEvent(
            @PathVariable Long id, 
            @RequestBody AcademicEvent event) {
        AcademicEvent updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }
    
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<AcademicEvent>> getEventsByBranch(@PathVariable Long branchId) {
        List<AcademicEvent> events = eventService.getEventsByBranch(branchId);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AcademicEvent> getEventById(@PathVariable Long id) {
        AcademicEvent event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    @GetMapping
    public ResponseEntity<List<AcademicEvent>> getAllEvents() {
        List<AcademicEvent> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}