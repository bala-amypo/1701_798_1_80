package com.example.demo.service.impl;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {

    private final AcademicEventRepository repo;

    public AcademicEventServiceImpl(AcademicEventRepository repo) {
        this.repo = repo;
    }

    public AcademicEvent createEvent(AcademicEvent e) {
        if (e.getStartDate().isAfter(e.getEndDate()))
            throw new ValidationException("startDate cannot be after endDate");
        return repo.save(e);
    }

    public List<AcademicEvent> getEventsByBranch(Long id) {
        return repo.findByBranchId(id);
    }
}
