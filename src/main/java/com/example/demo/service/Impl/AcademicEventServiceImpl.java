package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.service.AcademicEventService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {

    private final AcademicEventRepository repository;

    public AcademicEventServiceImpl(AcademicEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public AcademicEvent createEvent(AcademicEvent event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new ValidationException("startDate cannot be after endDate");
        }
        return repository.save(event);
    }

    @Override
    public List<AcademicEvent> getEventsByBranch(Long branchId) {
        return repository.findByBranchId(branchId);
    }

    @Override
    public List<AcademicEvent> findAllEvents() {
        return repository.findAll();
    }

    @Override
    public AcademicEvent saveEvent(AcademicEvent event) {
        return repository.save(event);
    }
}





// package com.example.demo.service.impl;

// @Service
// public class AcademicEventServiceImpl implements AcademicEventService {

//     private final AcademicEventRepository repo;

//     public AcademicEventServiceImpl(AcademicEventRepository repo) {
//         this.repo = repo;
//     }

//     public AcademicEvent createEvent(AcademicEvent e) {
//         if (e.getStartDate().isAfter(e.getEndDate()))
//             throw new ValidationException("startDate cannot be after endDate");
//         return repo.save(e);
//     }

//     public List<AcademicEvent> getEventsByBranch(Long id) {
//         return repo.findByBranchId(id);
//     }
// }
