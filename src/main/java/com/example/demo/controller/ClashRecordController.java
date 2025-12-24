package com.example.demo.controller;

import com.example.demo.entity.ClashRecord;
import com.example.demo.service.ClashDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clash-records")
public class ClashRecordController {
    
    @Autowired
    private ClashDetectionService clashService;
    
    @PostMapping
    public ClashRecord createClashRecord(@RequestBody ClashRecord clashRecord) {
        clashService.logClash(clashRecord);  // This should now work
        return clashRecord;
    }
    
    @PutMapping("/{id}/resolve")
    public void resolveClash(@PathVariable Long id, @RequestBody String resolutionNotes) {
        // Get resolution notes from request body
        clashService.resolveClash(id, resolutionNotes);  // Now passing 2 parameters
    }
    
    @GetMapping("/event/{eventId}")
    public List<ClashRecord> getClashesForEvent(@PathVariable Long eventId) {
        return clashService.getClashesForEvent(eventId);  // This should now work
    }
}