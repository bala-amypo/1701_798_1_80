// src/main/java/com/example/demo/controller/ClashRecordController.java
package com.example.demo.controller;

import com.example.demo.entity.ClashRecord;
import com.example.demo.service.ClashDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clashes")
@Tag(name = "Clash Records", description = "Clash detection and resolution endpoints")
public class ClashRecordController {
    
    private final ClashDetectionService clashService;
    
    public ClashRecordController(ClashDetectionService clashService) {
        this.clashService = clashService;
    }
    
    @PostMapping
    @Operation(summary = "Log a clash", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<ClashRecord> logClash(@RequestBody ClashRecord clash) {
        ClashRecord loggedClash = clashService.logClash(clash);
        return ResponseEntity.status(HttpStatus.CREATED).body(loggedClash);
    }
    
    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve a clash", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<ClashRecord> resolveClash(@PathVariable Long id) {
        ClashRecord resolvedClash = clashService.resolveClash(id);
        return ResponseEntity.ok(resolvedClash);
    }
    
    @GetMapping("/event/{eventId}")
    @Operation(summary = "Get clashes for a specific event", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<ClashRecord>> getClashesForEvent(@PathVariable Long eventId) {
        List<ClashRecord> clashes = clashService.getClashesForEvent(eventId);
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping("/unresolved")
    @Operation(summary = "List unresolved clashes", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<ClashRecord>> getUnresolvedClashes() {
        List<ClashRecord> clashes = clashService.getUnresolvedClashes();
        return ResponseEntity.ok(clashes);
    }
    
    @GetMapping
    @Operation(summary = "List all clashes", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<ClashRecord>> getAllClashes() {
        List<ClashRecord> clashes = clashService.getAllClashes();
        return ResponseEntity.ok(clashes);
    }
}