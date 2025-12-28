// package com.example.demo.service;

// import com.example.demo.entity.AcademicEvent;

// import java.util.List;

// public interface AcademicEventService {

//     AcademicEvent createEvent(AcademicEvent event);

//     AcademicEvent getEvent(Long id);

//     List<AcademicEvent> getAllEvents();

//     List<AcademicEvent> getEventsByBranch(Long branchId);
// }
package com.example.demo.service;

import com.example.demo.entity.AcademicEvent;

import java.util.List;

public interface AcademicEventService {

    AcademicEvent createEvent(AcademicEvent event);
    List<AcademicEvent> getEventsByBranch(Long branchId);
    List<AcademicEvent> getAllEvents();
    AcademicEvent getEvent(Long id);

}
