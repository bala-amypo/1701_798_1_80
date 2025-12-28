// package com.example.demo.service.impl;

// import com.example.demo.entity.HarmonizedCalendar;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.HarmonizedCalendarRepository;
// import com.example.demo.service.HarmonizedCalendarService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// @Transactional
// public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {
    
//     private final HarmonizedCalendarRepository harmonizedCalendarRepository;
    
//     @Autowired
//     public HarmonizedCalendarServiceImpl(HarmonizedCalendarRepository harmonizedCalendarRepository) {
//         this.harmonizedCalendarRepository = harmonizedCalendarRepository;
//     }
    
//     @Override
//     public HarmonizedCalendar generateHarmonizedCalendar(String title, String generatedBy) {
//         HarmonizedCalendar calendar = new HarmonizedCalendar();
//         calendar.setTitle(title);
//         calendar.setGeneratedBy(generatedBy);
//         calendar.setEffectiveFrom(LocalDate.now());
//         calendar.setEffectiveTo(LocalDate.now().plusMonths(3));
//         calendar.setEventsJson("[]");
        
//         return harmonizedCalendarRepository.save(calendar);
//     }
    
//     @Override
//     public HarmonizedCalendar getCalendarById(Long id) {
//         return harmonizedCalendarRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Calendar not found with id: " + id));
//     }
    
//     @Override
//     public List<HarmonizedCalendar> getAllCalendars() {
//         return harmonizedCalendarRepository.findAll();
//     }
    
//     @Override
//     public List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate start, LocalDate end) {
//         return harmonizedCalendarRepository.findCalendarsWithinRange(start, end);
//     }
//}

// package com.example.demo.service.impl;

// import com.example.demo.entity.AcademicEvent;
// import com.example.demo.entity.HarmonizedCalendar;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.AcademicEventRepository;
// import com.example.demo.repository.HarmonizedCalendarRepository;
// import com.example.demo.service.HarmonizedCalendarService;

// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {

//     private final HarmonizedCalendarRepository calendarRepo;
//     private final AcademicEventRepository eventRepo;

//     public HarmonizedCalendarServiceImpl(HarmonizedCalendarRepository calendarRepo,
//                                          AcademicEventRepository eventRepo) {
//         this.calendarRepo = calendarRepo;
//         this.eventRepo = eventRepo;
//     }

//     @Override
//     public HarmonizedCalendar generateCalendar(String title, String generatedBy) {

//         List<AcademicEvent> events = eventRepo.findAll();

//         HarmonizedCalendar calendar = new HarmonizedCalendar();
//         calendar.setTitle(title);
//         calendar.setGeneratedBy(generatedBy);
//         calendar.setEventsJson(events.toString());

//         return calendarRepo.save(calendar);
//     }

//     @Override
//     public List<HarmonizedCalendar> getAllCalendars() {
//         return calendarRepo.findAll();
//     }

//     @Override
//     public HarmonizedCalendar getCalendar(Long id) {
//         return calendarRepo.findById(id)
//                 .orElseThrow(() ->
//                         new ResourceNotFoundException("Calendar not found"));
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.service.HarmonizedCalendarService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/harmonized-calendar")
@SecurityRequirement(name = "bearerAuth")
public class HarmonizedCalendarController {

    private final HarmonizedCalendarService calendarService;

    public HarmonizedCalendarController(HarmonizedCalendarService calendarService) {
        this.calendarService = calendarService;
    }
    
    @PostMapping("/generate/{title}/{by}")
    public HarmonizedCalendar generateCalendar(@PathVariable String title, @PathVariable String by) {
        return calendarService.generateHarmonizedCalendar(title, by);
    }
    // @PostMapping
    // public ResponseEntity<HarmonizedCalendar> generateCalendar(
    //         @RequestParam String title,
    //         @RequestParam String generatedBy) {

    //     return ResponseEntity.ok(
    //             calendarService.generateCalendar(title, generatedBy)
    //     );
    // }

    @GetMapping
    public ResponseEntity<List<HarmonizedCalendar>> getAllCalendars() {
        return ResponseEntity.ok(calendarService.getAllCalendars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarmonizedCalendar> getCalendar(@PathVariable Long id) {
        return ResponseEntity.ok(calendarService.getCalendar(id));
    }
}
