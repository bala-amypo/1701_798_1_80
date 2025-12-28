// package com.example.demo.controller;

// import com.example.demo.entity.HarmonizedCalendar;
// import com.example.demo.service.HarmonizedCalendarService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import java.time.LocalDate;
// import java.util.List;

// @RestController
// @RequestMapping("/api/harmonized-calendars")
// @Tag(name = "Harmonized Calendars", description = "Harmonized calendar management APIs")
// public class HarmonizedCalendarController {
    
//     @Autowired
//     private HarmonizedCalendarService harmonizedCalendarService;
    
//     @PostMapping("/generate")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER')")
//     @Operation(summary = "Generate a harmonized calendar")
//     public ResponseEntity<HarmonizedCalendar> generateHarmonizedCalendar(
//             @RequestParam String title,
//             @RequestParam String generatedBy) {
//         HarmonizedCalendar calendar = harmonizedCalendarService.generateHarmonizedCalendar(title, generatedBy);
//         return ResponseEntity.ok(calendar);
//     }
    
//     @GetMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "Get calendar by ID")
//     public ResponseEntity<HarmonizedCalendar> getCalendarById(@PathVariable Long id) {
//         HarmonizedCalendar calendar = harmonizedCalendarService.getCalendarById(id);
//         return ResponseEntity.ok(calendar);
//     }
    
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "List all calendars")
//     public ResponseEntity<List<HarmonizedCalendar>> getAllCalendars() {
//         List<HarmonizedCalendar> calendars = harmonizedCalendarService.getAllCalendars();
//         return ResponseEntity.ok(calendars);
//     }
    
//     @GetMapping("/range")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "Get calendars within date range")
//     public ResponseEntity<List<HarmonizedCalendar>> getCalendarsWithinRange(
//             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
//             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
//         List<HarmonizedCalendar> calendars = harmonizedCalendarService.getCalendarsWithinRange(start, end);
//         return ResponseEntity.ok(calendars);
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
