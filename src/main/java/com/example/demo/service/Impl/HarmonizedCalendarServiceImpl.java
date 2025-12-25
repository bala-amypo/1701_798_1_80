package com.example.demo.service.Impl;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {
    
    @Autowired
    private HarmonizedCalendarRepository calendarRepository;
    
    @Override
    public HarmonizedCalendar createCalendar(HarmonizedCalendar calendar) {
        calendar.setCalendarId(System.currentTimeMillis());
        calendar.setIsActive(true);
        calendar.setCreatedAt(java.time.LocalDateTime.now());
        calendar.setUpdatedAt(java.time.LocalDateTime.now());
        return calendarRepository.save(calendar);
    }
    
    @Override
    public HarmonizedCalendar getCalendarById(Long id) {
        return calendarRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<HarmonizedCalendar> getAllCalendars() {
        return calendarRepository.findAll();
    }
    
    @Override
    public HarmonizedCalendar updateCalendar(Long id, HarmonizedCalendar calendarDetails) {
        HarmonizedCalendar calendar = calendarRepository.findById(id).orElse(null);
        if (calendar != null) {
            calendar.setCalendarName(calendarDetails.getCalendarName());
            calendar.setDescription(calendarDetails.getDescription());
            calendar.setEffectiveFrom(calendarDetails.getEffectiveFrom());
            calendar.setEffectiveTo(calendarDetails.getEffectiveTo());
            calendar.setPriority(calendarDetails.getPriority());
            calendar.setIsActive(calendarDetails.getIsActive());
            calendar.setUpdatedAt(java.time.LocalDateTime.now());
            return calendarRepository.save(calendar);
        }
        return null;
    }
    
    @Override
    public boolean deleteCalendar(Long id) {
        if (calendarRepository.existsById(id)) {
            calendarRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Override
    public List<HarmonizedCalendar> getCalendarsByDateRange(LocalDate startDate, LocalDate endDate) {
        return calendarRepository.findByEffectiveFromBetween(startDate, endDate);
    }
    
    @Override
    public List<HarmonizedCalendar> searchCalendarsByName(String name) {
        return calendarRepository.findByCalendarNameContaining(name);
    }
    
    @Override
    public List<HarmonizedCalendar> getActiveCalendars() {
        return calendarRepository.findByIsActive(true);
    }
    
    @Override
    public List<HarmonizedCalendar> getCalendarsByPriority(Integer priority) {
        return calendarRepository.findByPriority(priority);
    }
}