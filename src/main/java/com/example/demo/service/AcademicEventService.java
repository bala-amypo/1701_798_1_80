package com.example.demo.service;

import com.example.demo.entity.AcademicEvent;
import java.util.List;

public interface AcademicEventService {
    AcademicEvent createEvent(AcademicEvent academicEvent);
    AcademicEvent getEventById(Long id);
    List<AcademicEvent> getAllEvents();
    AcademicEvent updateEvent(Long id, AcademicEvent eventDetails);
    boolean deleteEvent(Long id);
}