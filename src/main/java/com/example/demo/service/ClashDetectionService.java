// // package com.example.demo.service;

// // import com.example.demo.entity.ClashRecord;

// // import java.util.List;

// // public interface ClashDetectionService {

// //     List<ClashRecord> detectClashes();

// //     ClashRecord resolveClash(Long id);
// // }




package com.example.demo.service;

import com.example.demo.entity.ClashRecord;
import java.util.List;

public interface ClashDetectionService {


   
    List<ClashRecord> detectClashes();
    List<ClashRecord> getClashesForEvent(Long eventId);
    List<ClashRecord> getUnresolvedClashes();
    ClashRecord resolveClash(Long id);
}


   

// package com.example.demo.service;

// import com.example.demo.entity.ClashRecord;

// import java.util.List;

// public interface ClashDetectionService {

 //   ClashRecord logClash(ClashRecord clashRecord);

//     ClashRecord resolveClash(Long id);

//     List<ClashRecord> getClashesByEvent(Long eventId);

//     List<ClashRecord> getUnresolvedClashes();

//     List<ClashRecord> getAllClashes();
// }





