// package com.example.demo.service.impl;

// import com.example.demo.entity.AcademicEvent;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.exception.ValidationException;
// import com.example.demo.repository.AcademicEventRepository;
// import com.example.demo.service.AcademicEventService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// @Transactional
// public class AcademicEventServiceImpl implements AcademicEventService {
    
//     private final AcademicEventRepository academicEventRepository;
    
//     @Autowired
//     public AcademicEventServiceImpl(AcademicEventRepository academicEventRepository) {
//         this.academicEventRepository = academicEventRepository;
//     }
    
//     @Override
//     public AcademicEvent createEvent(AcademicEvent event) {
//         if (event.getStartDate().isAfter(event.getEndDate())) {
//             throw new ValidationException("startDate cannot be after endDate");
//         }
//         return academicEventRepository.save(event);
//     }
    
//     @Override
//     public List<AcademicEvent> getEventsByBranch(Long branchId) {
//         return academicEventRepository.findByBranchId(branchId);
//     }
    
//     @Override
//     public AcademicEvent updateEvent(Long id, AcademicEvent event) {
//         AcademicEvent existing = academicEventRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        
//         if (event.getStartDate().isAfter(event.getEndDate())) {
//             throw new ValidationException("startDate cannot be after endDate");
//         }
        
//         existing.setTitle(event.getTitle());
//         existing.setEventType(event.getEventType());
//         existing.setStartDate(event.getStartDate());
//         existing.setEndDate(event.getEndDate());
//         existing.setLocation(event.getLocation());
//         existing.setDescription(event.getDescription());
        
//         return academicEventRepository.save(existing);
//     }
    
//     @Override
//     public AcademicEvent getEventById(Long id) {
//         return academicEventRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
//     }
    
//     @Override
//     public List<AcademicEvent> getAllEvents() {
//         return academicEventRepository.findAll();
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
