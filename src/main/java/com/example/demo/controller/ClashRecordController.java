package com.example.demo.controller;

import com.example.demo.entity.ClashRecord;
import com.example.demo.service.ClashDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clash-records")
public class ClashRecordController {
    
    @Autowired
    private ClashDetectionService clashDetectionService;
    
    @GetMapping
    public ResponseEntity<List<ClashRecord>> getAllClashRecords() {
        return ResponseEntity.ok(clashDetectionService.getAllClashRecords());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClashRecord> getClashRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(clashDetectionService.getClashRecordById(id));
    }
    
    @PostMapping("/detect")
    public ResponseEntity<List<ClashRecord>> detectClashes() {
        return ResponseEntity.ok(clashDetectionService.detectClashes());
    }
    
    @PostMapping
    public ResponseEntity<ClashRecord> createClashRecord(@RequestBody ClashRecord clashRecord) {
        return ResponseEntity.ok(clashDetectionService.saveClashRecord(clashRecord));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<ClashRecord> updateClashRecordStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(clashDetectionService.updateClashRecordStatus(id, status));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClashRecord(@PathVariable Long id) {
        clashDetectionService.deleteClashRecord(id);
        return ResponseEntity.noContent().build();
    }
}