package com.example.demo.service;

import com.example.demo.entity.ClashRecord;
import java.util.List;

public interface ClashDetectionService {
    ClashRecord detectClash(Long eventId1, Long eventId2, String clashType, String severity);
    List<ClashRecord> getClashesByEvent(Long eventId);
    List<ClashRecord> getAllClashes();
    ClashRecord resolveClash(Long clashId, String resolution);
    List<ClashRecord> getUnresolvedClashes();
    List<ClashRecord> getClashesByType(String clashType);
}