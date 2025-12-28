// // package com.example.demo.service.impl;

// // import com.example.demo.entity.AcademicEvent;
// // import com.example.demo.entity.ClashRecord;
// // import com.example.demo.exception.ResourceNotFoundException;
// // import com.example.demo.repository.AcademicEventRepository;
// // import com.example.demo.repository.ClashRecordRepository;
// // import com.example.demo.service.ClashDetectionService;

// // import org.springframework.stereotype.Service;

// // import java.util.ArrayList;
// // import java.util.List;

// // @Service
// // public class ClashDetectionServiceImpl implements ClashDetectionService {

// //     private final AcademicEventRepository eventRepo;
// //     private final ClashRecordRepository clashRepo;

// //     public ClashDetectionServiceImpl(AcademicEventRepository eventRepo,
// //                                      ClashRecordRepository clashRepo) {
// //         this.eventRepo = eventRepo;
// //         this.clashRepo = clashRepo;
// //     }

// //     @Override
// //     public List<ClashRecord> detectClashes() {

// //         List<AcademicEvent> events = eventRepo.findAll();
// //         List<ClashRecord> clashes = new ArrayList<>();

// //         for (int i = 0; i < events.size(); i++) {
// //             for (int j = i + 1; j < events.size(); j++) {

// //                 AcademicEvent a = events.get(i);
// //                 AcademicEvent b = events.get(j);

// //                 if (!a.getEndDate().isBefore(b.getStartDate()) &&
// //                     !b.getEndDate().isBefore(a.getStartDate())) {

// //                     ClashRecord clash = new ClashRecord();
// //                     clash.setEventAId(a.getId());
// //                     clash.setEventBId(b.getId());
// //                     clash.setClashType("DATE_OVERLAP");
// //                     clash.setSeverity("HIGH");
// //                     clash.setDetails("Event dates overlap");

// //                     clashes.add(clashRepo.save(clash));
// //                 }
// //             }
// //         }
// //         return clashes;
// //     }

// //     @Override
// //     public ClashRecord resolveClash(Long id) {

// //         ClashRecord clash = clashRepo.findById(id)
// //                 .orElseThrow(() ->
// //                         new ResourceNotFoundException("Clash not found"));

// //         clash.setResolved(true);
// //         return clashRepo.save(clash);
// //     }
    
// // }





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


// package com.example.demo.service.impl;

// import com.example.demo.entity.ClashRecord;
// import com.example.demo.repository.ClashRecordRepository;
// import com.example.demo.service.ClashDetectionService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ClashDetectionServiceImpl implements ClashDetectionService {

//     private final ClashRecordRepository clashRepository;

//     public ClashDetectionServiceImpl(ClashRecordRepository clashRepository) {
//         this.clashRepository = clashRepository;
//     }

    // @Override
    // public ClashRecord logClash(ClashRecord clashRecord) {
    //     clashRecord.setResolved(false);
    //     return clashRepository.save(clashRecord);
    // }

//     @Override
//     public ClashRecord resolveClash(Long id) {
//         ClashRecord clash = clashRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Clash not found"));
//         clash.setResolved(true);
//         return clashRepository.save(clash);
//     }

//     @Override
//     public List<ClashRecord> getClashesByEvent(Long eventId) {
//         return clashRepository.findByEventId(eventId);
//     }

//     @Override
//     public List<ClashRecord> getUnresolvedClashes() {
//         return clashRepository.findByResolvedFalse();
//     }

//     @Override
//     public List<ClashRecord> getAllClashes() {
//         return clashRepository.findAll();
//     }
//}



