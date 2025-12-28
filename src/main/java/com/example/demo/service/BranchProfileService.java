// package com.example.demo.service;

// import com.example.demo.entity.BranchProfile;

// import java.util.List;

// public interface BranchProfileService {

//     BranchProfile createBranch(BranchProfile branch);

//     BranchProfile getBranch(Long id);

//     List<BranchProfile> getAllBranches();

//     BranchProfile updateBranch(Long id, BranchProfile branch);
// }
package com.example.demo.service;

import com.example.demo.entity.BranchProfile;
import java.util.List;

public interface BranchProfileService {

    BranchProfile createBranch(BranchProfile branch);
    BranchProfile updateBranchStatus(Long id, boolean status);
    List<BranchProfile> getAllBranches();
    BranchProfile updateBranch(Long id, BranchProfile updated) ;
    BranchProfile getBranch(Long id);

}
