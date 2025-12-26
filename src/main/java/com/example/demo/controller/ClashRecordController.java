package com.example.demo.controller;

import com.example.demo.entity.ClashRecord;
import com.example.demo.service.ClashDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clashes")
@Tag(name = "Clash Records", description = "Clash detection and resolution APIs")
public class ClashRecordController {
    
    @Autowired
    private ClashDetectionService clashDetectionService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER')")
    @Operation(summary = "Log a clash")
    public ResponseEntity<ClashRecord> logClash(@RequestBody ClashRecord clash) {
        ClashRecord logged = clashDetectionService.logClash(clash);
        return ResponseEntity.ok(logged);
    }
    
    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER')")
    @Operation(summary = "Resolve a clash")
    public ResponseEntity<ClashRecord> resolveClash(@PathVariable Long id) {
        ClashRecord resolved = clashDetectionService.resolveClash(id);
        return ResponseEntity.ok(resolved);
    }
    
    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
    @Operation(summary = "Get clashes for a specific event")
    public ResponseEntity<List<ClashRecord>> getClashesForEvent(@PathVariable Long eventId) {
        List<ClashRecord> clashes = clashDetectionService.getClashesForEvent(eventId);
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping("/unresolved")
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
    @Operation(summary = "List unresolved clashes")
    public ResponseEntity<List<ClashRecord>> getUnresolvedClashes() {
        List<ClashRecord> clashes = clashDetectionService.getUnresolvedClashes();
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
    @Operation(summary = "List all clashes")
    public ResponseEntity<List<ClashRecord>> getAllClashes() {
        List<ClashRecord> clashes = clashDetectionService.getAllClashes();
        return ResponseEntity.ok(clashes);
    }
}