package com.example.demo.service;

import com.example.demo.entity.ClashRecord;
import java.util.List;

public interface ClashDetectionService {
    ClashRecord detectClash(Long eventId1, Long eventId2, String description);
    List<ClashRecord> getUnresolvedClashes();
    ClashRecord resolveClash(Long clashId, String resolution);  // Two parameters
    List<ClashRecord> getAllClashes();
    ClashRecord getClashById(Long id);
}