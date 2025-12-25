package com.example.demo.controller;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.service.AcademicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/academic-events")
public class AcademicEventController {
    
    @Autowired
    private AcademicEventService academicEventService;
    
    @PostMapping
    public ResponseEntity<AcademicEvent> createEvent(@RequestBody AcademicEvent academicEvent) {
        AcademicEvent createdEvent = academicEventService.createEvent(academicEvent);
        return ResponseEntity.ok(createdEvent);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AcademicEvent> getEventById(@PathVariable Long id) {
        AcademicEvent academicEvent = academicEventService.getEventById(id);
        if (academicEvent != null) {
            return ResponseEntity.ok(academicEvent);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<AcademicEvent>> getAllEvents() {
        List<AcademicEvent> events = academicEventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AcademicEvent> updateEvent(@PathVariable Long id, @RequestBody AcademicEvent eventDetails) {
        AcademicEvent updatedEvent = academicEventService.updateEvent(id, eventDetails);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean deleted = academicEventService.deleteEvent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}