package com.example.demo.service.impl;

import com.example.demo.entity.BranchProfile;
import com.example.demo.repository.BranchProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BranchProfileServiceImpl {
    private final BranchProfileRepository branchProfileRepository;

    public BranchProfileServiceImpl(BranchProfileRepository branchProfileRepository) {
        this.branchProfileRepository = branchProfileRepository;
    }

    public BranchProfile createBranch(BranchProfile branch) {
        return branchProfileRepository.save(branch);
    }

    public BranchProfile updateBranchStatus(Long id, Boolean active) {
        BranchProfile branch = branchProfileRepository.findById(id).orElseThrow();
        branch.setActive(active);
        return branchProfileRepository.save(branch);
    }

    public List<BranchProfile> getAllBranches() {
        return branchProfileRepository.findAll();
    }
}