// package com.example.demo.service;

// import com.example.demo.entity.BranchProfile;
// import java.util.List;

// public interface BranchProfileService {
//     BranchProfile createBranch(BranchProfile branchProfile);
//     BranchProfile getBranchById(Long id);
//     List<BranchProfile> getAllBranches();
//     BranchProfile updateBranch(Long id, BranchProfile branchDetails);
//     boolean deleteBranch(Long id);
// }
package com.example.demo.service;

import com.example.demo.entity.BranchProfile;

import java.util.List;

public interface BranchProfileService {

    BranchProfile createBranch(BranchProfile branch);

    BranchProfile updateBranchStatus(Long id, boolean active);

    List<BranchProfile> getAllBranches();

    BranchProfile getBranchById(Long id);

    BranchProfile findByBranchCode(String branchCode);
}
