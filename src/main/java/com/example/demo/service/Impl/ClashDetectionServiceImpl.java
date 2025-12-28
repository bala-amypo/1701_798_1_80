// package com.example.demo.service.impl;

// import com.example.demo.entity.ClashRecord;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.ClashRecordRepository;
// import com.example.demo.service.ClashDetectionService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.List;

// @Service
// @Transactional
// public class ClashDetectionServiceImpl implements ClashDetectionService {
    
//     private final ClashRecordRepository clashRecordRepository;
    
//     @Autowired
//     public ClashDetectionServiceImpl(ClashRecordRepository clashRecordRepository) {
//         this.clashRecordRepository = clashRecordRepository;
//     }
    
//     @Override
//     public ClashRecord logClash(ClashRecord clash) {
//         return clashRecordRepository.save(clash);
//     }
    
//     @Override
//     public List<ClashRecord> getClashesForEvent(Long eventId) {
//         return clashRecordRepository.findByEventAIdOrEventBId(eventId, eventId);
//     }
    
//     @Override
//     public ClashRecord resolveClash(Long clashId) {
//         ClashRecord clash = clashRecordRepository.findById(clashId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Clash record not found with id: " + clashId));
//         clash.setResolved(true);
//         return clashRecordRepository.save(clash);
//     }
    
//     @Override
//     public List<ClashRecord> getUnresolvedClashes() {
//         return clashRecordRepository.findByResolvedFalse();
//     }
    
//     @Override
//     public List<ClashRecord> getAllClashes() {
//         return clashRecordRepository.findAll();
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.ClashRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClashRecordRepository;
import com.example.demo.service.ClashDetectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClashDetectionServiceImpl implements ClashDetectionService {

    private final ClashRecordRepository repo;

    public ClashDetectionServiceImpl(ClashRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ClashRecord> detectClashes() {
        return repo.findAll();
    }

    @Override
    public List<ClashRecord> getClashesForEvent(Long id) {
        return repo.findByEventAIdOrEventBId(id, id);
    }

    @Override
    public List<ClashRecord> getUnresolvedClashes() {
        return repo.findByResolvedFalse();

    }

    @Override
    public ClashRecord resolveClash(Long id) {
        ClashRecord cr = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clash not found"));
        cr.setResolved(true);
        return repo.save(cr);
    }


}