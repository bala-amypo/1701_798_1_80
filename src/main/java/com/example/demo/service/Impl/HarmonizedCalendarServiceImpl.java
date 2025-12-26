package com.example.demo.service.impl;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.HarmonizedCalendarRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class HarmonizedCalendarServiceImpl {
    private final HarmonizedCalendarRepository harmonizedCalendarRepository;

    public HarmonizedCalendarServiceImpl(HarmonizedCalendarRepository harmonizedCalendarRepository) {
        this.harmonizedCalendarRepository = harmonizedCalendarRepository;
    }

    public HarmonizedCalendar generateHarmonizedCalendar(String title, String generatedBy) {
        HarmonizedCalendar calendar = new HarmonizedCalendar();
        calendar.setTitle(title);
        calendar.setGeneratedBy(generatedBy);
        calendar.setEffectiveFrom(LocalDate.now());
        calendar.setEffectiveTo(LocalDate.now().plusDays(30));
        calendar.setEventsJson("[]");
        return harmonizedCalendarRepository.save(calendar);
    }

    public List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate start, LocalDate end) {
        return harmonizedCalendarRepository.findByEffectiveFromLessThanEqualAndEffectiveToGreaterThanEqual(start, end);
    }
}