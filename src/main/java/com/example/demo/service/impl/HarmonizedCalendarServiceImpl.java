package com.example.demo.service.impl;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HarmonizedCalendarServiceimpl implements HarmonizedCalendarService {

    private final HarmonizedCalendarRepository repository;

    public HarmonizedCalendarServiceImpl(HarmonizedCalendarRepository repository) {
        this.repository = repository;
    }

    @Override
    public HarmonizedCalendar generateHarmonizedCalendar(
            String title,
            LocalDate startDate,
            LocalDate endDate,
            String generatedBy
    ) {
        HarmonizedCalendar calendar = new HarmonizedCalendar();
        calendar.setTitle(title);
        calendar.setStartDate(startDate);
        calendar.setEndDate(endDate);
        calendar.setGeneratedBy(generatedBy);

        return repository.save(calendar);
    }

    @Override
    public HarmonizedCalendar getCalendarById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<HarmonizedCalendar> getAllCalendars() {
        return repository.findAll();
    }
}
