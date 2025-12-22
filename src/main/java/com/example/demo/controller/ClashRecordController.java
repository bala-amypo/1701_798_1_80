package com.example.demo.controller;

import com.example.demo.entity.ClashRecord;
import com.example.demo.service.ClashDetectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clashes")
public class ClashRecordController {
    
    private final ClashDetectionService clashService;
    
    public ClashRecordController(ClashDetectionService clashService) {
        this.clashService = clashService;
    }
    
    @PostMapping
    public ResponseEntity<ClashRecord> logClash(@RequestBody ClashRecord clash) {
        ClashRecord loggedClash = clashService.logClash(clash);
        return ResponseEntity.status(HttpStatus.CREATED).body(loggedClash);
    }
    
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ClashRecord> resolveClash(@PathVariable Long id) {
        ClashRecord resolvedClash = clashService.resolveClash(id);
        return ResponseEntity.ok(resolvedClash);
    }
    
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<ClashRecord>> getClashesForEvent(@PathVariable Long eventId) {
        List<ClashRecord> clashes = clashService.getClashesForEvent(eventId);
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping("/unresolved")
    public ResponseEntity<List<ClashRecord>> getUnresolvedClashes() {
        List<ClashRecord> clashes = clashService.getUnresolvedClashes();
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping
    public ResponseEntity<List<ClashRecord>> getAllClashes() {
        List<ClashRecord> clashes = clashService.getAllClashes();
        return ResponseEntity.ok(clashes);
    }
}