// package com.example.demo.service;

// import com.example.demo.entity.AcademicEvent;
// import java.util.List;
// import java.util.Optional;

// public interface AcademicEventService {
//     List<AcademicEvent> getAllAcademicEvents();
//     Optional<AcademicEvent> getAcademicEventById(Long id);
//     AcademicEvent saveAcademicEvent(AcademicEvent academicEvent);
//     AcademicEvent updateAcademicEvent(Long id, AcademicEvent academicEvent);
//     void deleteAcademicEvent(Long id);
// }
package com.example.demo.service;

import com.example.demo.entity.AcademicEvent;

import java.util.List;

public interface AcademicEventService {

    AcademicEvent createEvent(AcademicEvent event);

    AcademicEvent updateEvent(Long id, AcademicEvent event);

    AcademicEvent getEventById(Long id);

    List<AcademicEvent> getAllEvents();

    List<AcademicEvent> getEventsByBranch(Long branchId);
}
