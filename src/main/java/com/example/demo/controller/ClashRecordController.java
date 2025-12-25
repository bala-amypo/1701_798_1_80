package com.example.demo.controller;

import com.example.demo.entity.ClashRecord;
import com.example.demo.service.ClashDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clashes")
public class ClashRecordController {
    
    @Autowired
    private ClashDetectionService clashService;
    
    @PostMapping("/detect")
    public ResponseEntity<ClashRecord> detectClash(
            @RequestParam Long eventId1,
            @RequestParam Long eventId2,
            @RequestParam String clashType,
            @RequestParam String severity) {
        ClashRecord clashRecord = clashService.detectClash(eventId1, eventId2, clashType, severity);
        return ResponseEntity.ok(clashRecord);
    }
    
    @PutMapping("/resolve/{clashId}")
    public ResponseEntity<ClashRecord> resolveClash(
            @PathVariable Long clashId,
            @RequestParam String resolution) {
        ClashRecord resolvedClash = clashService.resolveClash(clashId, resolution);
        if (resolvedClash != null) {
            return ResponseEntity.ok(resolvedClash);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<ClashRecord>> getClashesByEvent(@PathVariable Long eventId) {
        List<ClashRecord> clashes = clashService.getClashesByEvent(eventId);
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping
    public ResponseEntity<List<ClashRecord>> getAllClashes() {
        List<ClashRecord> clashes = clashService.getAllClashes();
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping("/unresolved")
    public ResponseEntity<List<ClashRecord>> getUnresolvedClashes() {
        List<ClashRecord> unresolvedClashes = clashService.getUnresolvedClashes();
        return ResponseEntity.ok(unresolvedClashes);
    }
    
    @GetMapping("/type/{clashType}")
    public ResponseEntity<List<ClashRecord>> getClashesByType(@PathVariable String clashType) {
        List<ClashRecord> clashes = clashService.getClashesByType(clashType);
        return ResponseEntity.ok(clashes);
    }
}