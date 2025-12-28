package com.example.demo.controller;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.service.AcademicEventService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@SecurityRequirement(name = "bearerAuth")
public class AcademicEventController {

    private final AcademicEventService eventService;

    public AcademicEventController(AcademicEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<AcademicEvent> createEvent(@RequestBody AcademicEvent event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicEvent> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @GetMapping
    public ResponseEntity<List<AcademicEvent>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<AcademicEvent>> getEventsByBranch(
            @PathVariable Long branchId) {
        return ResponseEntity.ok(eventService.getEventsByBranch(branchId));
    }
}
