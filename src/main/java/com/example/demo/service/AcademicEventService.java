package com.example.demo.service;

import com.example.demo.entity.AcademicEvent;
import java.util.List;

public interface AcademicEventService {
    AcademicEvent save(AcademicEvent event);
    List<AcademicEvent> findAll();
}
