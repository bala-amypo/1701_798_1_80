package com.example.demo.service.impl;

import com.example.demo.entity.BranchProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BranchProfileRepository;
import com.example.demo.service.BranchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BranchProfileServiceImpl implements BranchProfileService {
    
    private final BranchProfileRepository branchProfileRepository;
    
    @Autowired
    public BranchProfileServiceImpl(BranchProfileRepository branchProfileRepository) {
        this.branchProfileRepository = branchProfileRepository;
    }
    
    @Override
    public BranchProfile createBranch(BranchProfile branch) {
        return branchProfileRepository.save(branch);
    }
    
    @Override
    public BranchProfile updateBranchStatus(Long id, boolean active) {
        BranchProfile branch = branchProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
        branch.setActive(active);
        return branchProfileRepository.save(branch);
    }
    
    @Override
    public List<BranchProfile> getAllBranches() {
        return branchProfileRepository.findAll();
    }
    
    @Override
    public BranchProfile getBranchById(Long id) {
        return branchProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
    }
    
    @Override
    public Optional<BranchProfile> findByBranchCode(String branchCode) {
        return branchProfileRepository.findByBranchCode(branchCode);
    }
}