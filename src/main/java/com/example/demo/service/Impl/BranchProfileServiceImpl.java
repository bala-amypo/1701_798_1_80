package com.example.demo.service.impl;

import com.example.demo.entity.BranchProfile;
import com.example.demo.repository.BranchProfileRepository;
import com.example.demo.service.BranchProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BranchProfileServiceImpl implements BranchProfileService {
    private static final Logger log = LoggerFactory.getLogger(BranchProfileServiceImpl.class);
    private final BranchProfileRepository branchRepository;
    public BranchProfileServiceImpl(BranchProfileRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
    
    @Override public BranchProfile createBranch(BranchProfile branch) {
        String branchCode = branch.getBranchCode();
        if (branchRepository.findByBranchCode(branchCode).isPresent())
            throw new RuntimeException("Branch code exists: " + branchCode);
        log.info("Processing branch: {} with code: {}", branch.getBranchName(), branchCode);
        branch.setActive(true);
        branch.setLastSyncAt(LocalDateTime.now());
        return branchRepository.save(branch);
    }
    @Override public BranchProfile updateBranchStatus(Long id, boolean active) {
        BranchProfile branch = getBranchById(id);
        branch.setActive(active);
        branch.setLastSyncAt(LocalDateTime.now());
        return branchRepository.save(branch);
    }
    @Override public List<BranchProfile> getAllBranches() { return branchRepository.findAll(); }
    @Override public BranchProfile getBranchById(Long id) {
        return branchRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Branch not found: " + id));
    }
    @Override public BranchProfile findByBranchCode(String branchCode) {
        return branchRepository.findByBranchCode(branchCode)
            .orElseThrow(() -> new RuntimeException("Branch not found: " + branchCode));
    }
}