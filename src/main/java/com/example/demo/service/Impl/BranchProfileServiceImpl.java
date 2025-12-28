// package com.example.demo.service.impl;

// import com.example.demo.entity.BranchProfile;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.BranchProfileRepository;
// import com.example.demo.service.BranchProfileService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;

// @Service
// @Transactional
// public class BranchProfileServiceImpl implements BranchProfileService {
    
//     private final BranchProfileRepository branchProfileRepository;
    
//     @Autowired
//     public BranchProfileServiceImpl(BranchProfileRepository branchProfileRepository) {
//         this.branchProfileRepository = branchProfileRepository;
//     }
    
//     @Override
//     public BranchProfile createBranch(BranchProfile branch) {
//         return branchProfileRepository.save(branch);
//     }
    
//     @Override
//     public BranchProfile updateBranchStatus(Long id, boolean active) {
//         BranchProfile branch = branchProfileRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
//         branch.setActive(active);
//         return branchProfileRepository.save(branch);
//     }
    
//     @Override
//     public List<BranchProfile> getAllBranches() {
//         return branchProfileRepository.findAll();
//     }
    
//     @Override
//     public BranchProfile getBranchById(Long id) {
//         return branchProfileRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
//     }
    
//     @Override
//     public Optional<BranchProfile> findByBranchCode(String branchCode) {
//         return branchProfileRepository.findByBranchCode(branchCode);
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
