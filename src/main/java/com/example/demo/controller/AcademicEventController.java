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
    public ResponseEntity<AcademicEvent> createAcademicEvent(@RequestBody AcademicEvent academicEvent) {
        return ResponseEntity.ok(academicEventService.saveAcademicEvent(academicEvent));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AcademicEvent> getAcademicEventById(@PathVariable Long id) {
        return academicEventService.getAcademicEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<AcademicEvent>> getAllAcademicEvents() {
        return ResponseEntity.ok(academicEventService.getAllAcademicEvents());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AcademicEvent> updateAcademicEvent(@PathVariable Long id, @RequestBody AcademicEvent academicEvent) {
        AcademicEvent updated = academicEventService.updateAcademicEvent(id, academicEvent);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademicEvent(@PathVariable Long id) {
        academicEventService.deleteAcademicEvent(id);
        return ResponseEntity.noContent().build();
    }
}