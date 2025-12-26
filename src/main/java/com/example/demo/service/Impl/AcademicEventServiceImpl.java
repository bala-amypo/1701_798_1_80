package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AcademicEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcademicEventServiceImpl {
    private final AcademicEventRepository academicEventRepository;

    public AcademicEventServiceImpl(AcademicEventRepository academicEventRepository) {
        this.academicEventRepository = academicEventRepository;
    }

    public AcademicEvent createEvent(AcademicEvent event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new ValidationException("startDate cannot be after endDate");
        }
        return academicEventRepository.save(event);
    }

    public List<AcademicEvent> getEventsByBranch(Long branchId) {
        return academicEventRepository.findByBranchId(branchId);
    }
}