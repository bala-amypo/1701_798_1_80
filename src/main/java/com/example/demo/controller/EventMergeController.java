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
