package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.service.AcademicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {
    
    @Autowired
    private AcademicEventRepository academicEventRepository;
    
    @Override
    public List<AcademicEvent> getAllAcademicEvents() {
        return academicEventRepository.findAll();
    }
    
    @Override
    public Optional<AcademicEvent> getAcademicEventById(Long id) {
        return academicEventRepository.findById(id);
    }
    
    @Override
    public AcademicEvent saveAcademicEvent(AcademicEvent academicEvent) {
        return academicEventRepository.save(academicEvent);
    }
    
    @Override
    public AcademicEvent updateAcademicEvent(Long id, AcademicEvent academicEvent) {
        if (academicEventRepository.existsById(id)) {
            academicEvent.setId(id);
            return academicEventRepository.save(academicEvent);
        }
        return null;
    }
    
    @Override
    public void deleteAcademicEvent(Long id) {
        academicEventRepository.deleteById(id);
    }
}