package com.example.demo.Controller;

import com.example.demo.entity.EventMergeRecord;
import com.example.demo.service.EventMergeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/merge-records")
public class EventMergeController {
    
    private final EventMergeService mergeService;
    
    public EventMergeController(EventMergeService mergeService) {
        this.mergeService = mergeService;
    }
    
    @PostMapping
    public ResponseEntity<EventMergeRecord> mergeEvents(@RequestBody Map<String, Object> request) {
        @SuppressWarnings("unchecked")
        List<Long> eventIds = (List<Long>) request.get("eventIds");
        String reason = (String) request.get("reason");
        
        EventMergeRecord mergeRecord = mergeService.mergeEvents(eventIds, reason);
        return ResponseEntity.status(HttpStatus.CREATED).body(mergeRecord);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EventMergeRecord> getMergeRecordById(@PathVariable Long id) {
        EventMergeRecord mergeRecord = mergeService.getMergeRecordById(id);
        return ResponseEntity.ok(mergeRecord);
    }
    
    @GetMapping
    public ResponseEntity<List<EventMergeRecord>> getAllMergeRecords() {
        List<EventMergeRecord> mergeRecords = mergeService.getAllMergeRecords();
        return ResponseEntity.ok(mergeRecords);
    }
    
    @GetMapping("/range")
    public ResponseEntity<List<EventMergeRecord>> getMergeRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<EventMergeRecord> mergeRecords = mergeService.getMergeRecordsByDate(start, end);
        return ResponseEntity.ok(mergeRecords);
    }
}