package com.example.demo.service.impl;

import com.example.demo.exception.ValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.BranchProfile;
import com.example.demo.repository.BranchProfileRepository;
import com.example.demo.service.BranchProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BranchProfileServiceImpl implements BranchProfileService {
    
    private final BranchProfileRepository branchRepository;
    
    public BranchProfileServiceImpl(BranchProfileRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
    
    @Override
    public BranchProfile createBranch(BranchProfile branch) {
        if (branchRepository.findByBranchCode(branch.getBranchCode()).isPresent()) {
            throw new ValidationException("Branch code already exists: " + branch.getBranchCode());
        }
        
        branch.setActive(true);
        branch.setLastSyncAt(LocalDateTime.now());
        
        return branchRepository.save(branch);
    }
    
    @Override
    public BranchProfile updateBranchStatus(Long id, boolean active) {
        BranchProfile branch = getBranchById(id);
        branch.setActive(active);
        branch.setLastSyncAt(LocalDateTime.now());
        return branchRepository.save(branch);
    }
    
    @Override
    public List<BranchProfile> getAllBranches() {
        return branchRepository.findAll();
    }
    
    @Override
    public BranchProfile getBranchById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
    }
    
    @Override
    public BranchProfile findByBranchCode(String branchCode) {
        return branchRepository.findByBranchCode(branchCode)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with code: " + branchCode));
    }
}