// package com.example.demo.service.impl;

// import com.example.demo.entity.AcademicEvent;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.exception.ValidationException;
// import com.example.demo.repository.AcademicEventRepository;
// import com.example.demo.service.AcademicEventService;

// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class AcademicEventServiceImpl implements AcademicEventService {

//     private final AcademicEventRepository eventRepo;

//     public AcademicEventServiceImpl(AcademicEventRepository eventRepo) {
//         this.eventRepo = eventRepo;
//     }

//     @Override
//     public AcademicEvent createEvent(AcademicEvent event) {

//         if (event.getStartDate() != null &&
//             event.getEndDate() != null &&
//             event.getEndDate().isBefore(event.getStartDate())) {
//             throw new ValidationException("End date cannot be before start date");
//         }

//         return eventRepo.save(event);
//     }

//     @Override
//     public AcademicEvent getEvent(Long id) {
//         return eventRepo.findById(id)
//                 .orElseThrow(() ->
//                         new ResourceNotFoundException("Event not found"));
//     }

//     @Override
//     public List<AcademicEvent> getAllEvents() {
//         return eventRepo.findAll();
//     }

//     @Override
//     public List<AcademicEvent> getEventsByBranch(Long branchId) {
//         return eventRepo.findByBranchId(branchId);
//     }

    
// }
package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.service.AcademicEventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {

    private final AcademicEventRepository repo;

    public AcademicEventServiceImpl(AcademicEventRepository repo) {
        this.repo = repo;
    }

    @Override
    public AcademicEvent createEvent(AcademicEvent event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new ValidationException("startDate cannot be after endDate");
        }
        return repo.save(event);
    }

    @Override
    public List<AcademicEvent> getAllEvents() {
        return repo.findAll();
    }

    @Override
    public List<AcademicEvent> getEventsByBranch(Long branchId) {
        return repo.findByBranchId(branchId);
    }

    @Override
    public AcademicEvent getEvent(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

}
