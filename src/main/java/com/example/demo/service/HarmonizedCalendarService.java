// package com.example.demo.service;

// import com.example.demo.entity.HarmonizedCalendar;

// import java.util.List;

// public interface HarmonizedCalendarService {

//     HarmonizedCalendar generateCalendar(String title, String generatedBy);

//     List<HarmonizedCalendar> getAllCalendars();

//     HarmonizedCalendar getCalendar(Long id);
// }
package com.example.demo.service;

import com.example.demo.entity.HarmonizedCalendar;
import java.time.LocalDate;
import java.util.List;

public interface HarmonizedCalendarService {

    HarmonizedCalendar generateHarmonizedCalendar(String title, String user);
    List<HarmonizedCalendar> getAllCalendars();
    HarmonizedCalendar getCalendar(Long id);
    List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate start, LocalDate end);
}
