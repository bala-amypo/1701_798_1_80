package com.example.demo.service;

import com.example.demo.entity.AcademicEvent;
import java.util.List;
import java.util.Optional;

public interface AcademicEventService {
    List<AcademicEvent> getAllAcademicEvents();
    Optional<AcademicEvent> getAcademicEventById(Long id);
    AcademicEvent saveAcademicEvent(AcademicEvent academicEvent);
    AcademicEvent updateAcademicEvent(Long id, AcademicEvent academicEvent);
    void deleteAcademicEvent(Long id);
}