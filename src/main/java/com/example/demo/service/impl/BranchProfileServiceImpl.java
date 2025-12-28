// package com.example.demo.service.impl;

// import com.example.demo.entity.BranchProfile;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.exception.ValidationException;
// import com.example.demo.repository.BranchProfileRepository;
// import com.example.demo.service.BranchProfileService;

// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class BranchProfileServiceImpl implements BranchProfileService {

//     private final BranchProfileRepository branchRepo;

//     public BranchProfileServiceImpl(BranchProfileRepository branchRepo) {
//         this.branchRepo = branchRepo;
//     }

//     @Override
//     public BranchProfile createBranch(BranchProfile branch) {

//         if (branchRepo.existsByBranchCode(branch.getBranchCode())) {
//             throw new ValidationException("Branch code already exists");
//         }

//         return branchRepo.save(branch);
//     }

//     @Override
//     public BranchProfile getBranch(Long id) {
//         return branchRepo.findById(id)
//                 .orElseThrow(() ->
//                         new ResourceNotFoundException("Branch not found"));
//     }

//     @Override
//     public List<BranchProfile> getAllBranches() {
//         return branchRepo.findAll();
//     }

//     @Override
//     public BranchProfile updateBranch(Long id, BranchProfile branch) {

//         BranchProfile existing = getBranch(id);

//         existing.setBranchName(branch.getBranchName());
//         existing.setContactEmail(branch.getContactEmail());
//         existing.setActive(branch.getActive());

//         return branchRepo.save(existing);
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.BranchProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BranchProfileRepository;
import com.example.demo.service.BranchProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BranchProfileServiceImpl implements BranchProfileService {

    private final BranchProfileRepository repo;

    public BranchProfileServiceImpl(BranchProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public BranchProfile createBranch(BranchProfile bp) {
        bp.setLastSyncAt(LocalDateTime.now());
        return repo.save(bp);
    }

    @Override
    public BranchProfile updateBranch(Long id, BranchProfile updated) {
        BranchProfile bp = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        bp.setBranchName(updated.getBranchName());
        bp.setContactEmail(updated.getContactEmail());
        bp.setActive(updated.getActive());
        bp.setLastSyncAt(LocalDateTime.now());
        return repo.save(bp);
    }

    @Override
    public BranchProfile updateBranchStatus(Long id, boolean active) {
        BranchProfile bp = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
        bp.setActive(active);
        return repo.save(bp);
    }

    @Override
    public List<BranchProfile> getAllBranches() {
        return repo.findAll();
    }

    @Override
    public BranchProfile getBranch(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
    }

}
