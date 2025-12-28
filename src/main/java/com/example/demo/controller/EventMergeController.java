// package com.example.demo.controller;

// import com.example.demo.entity.EventMergeRecord;
// import com.example.demo.service.EventMergeService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @RequestMapping("/api/merge-records")
// @Tag(name = "Event Merge Records", description = "Event merge management APIs")
// public class EventMergeController {
    
//     @Autowired
//     private EventMergeService eventMergeService;
    
//     @PostMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER')")
//     @Operation(summary = "Merge multiple events")
//     public ResponseEntity<EventMergeRecord> mergeEvents(@RequestParam List<Long> eventIds, 
//                                                        @RequestParam String reason) {
//         EventMergeRecord merged = eventMergeService.mergeEvents(eventIds, reason);
//         return ResponseEntity.ok(merged);
//     }
    
//     @GetMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "Get merge record by ID")
//     public ResponseEntity<EventMergeRecord> getMergeRecordById(@PathVariable Long id) {
//         EventMergeRecord record = eventMergeService.getMergeRecordById(id);
//         return ResponseEntity.ok(record);
//     }
    
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "List all merges")
//     public ResponseEntity<List<EventMergeRecord>> getAllMergeRecords() {
//         List<EventMergeRecord> records = eventMergeService.getAllMergeRecords();
//         return ResponseEntity.ok(records);
//     }
    
//     @GetMapping("/range")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "Get merges by date range")
//     public ResponseEntity<List<EventMergeRecord>> getMergeRecordsByDate(
//             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
//             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
//         List<EventMergeRecord> records = eventMergeService.getMergeRecordsByDate(start, end);
//         return ResponseEntity.ok(records);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.EventMergeRecord;
import com.example.demo.service.EventMergeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/merge")
@SecurityRequirement(name = "bearerAuth")
public class EventMergeController {

    private final EventMergeService mergeService;

    public EventMergeController(EventMergeService mergeService) {
        this.mergeService = mergeService;
    }

    @PostMapping
    public ResponseEntity<EventMergeRecord> mergeEvents(
            @RequestParam List<Long> eventIds,
            @RequestParam String reason) {

        return ResponseEntity.ok(
                mergeService.mergeEvents(eventIds, reason)
        );
    }

    @GetMapping
    public ResponseEntity<List<EventMergeRecord>> getAllMerges() {
        return ResponseEntity.ok(mergeService.getAllMerges());
    }
}
