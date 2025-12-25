package com.example.demo.service.Impl;

import com.example.demo.entity.BranchProfile;
import com.example.demo.repository.BranchProfileRepository;
import com.example.demo.service.BranchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BranchProfileServiceImpl implements BranchProfileService {
    
    @Autowired
    private BranchProfileRepository branchRepository;
    
    @Override
    public BranchProfile createBranch(BranchProfile branchProfile) {
        return branchRepository.save(branchProfile);
    }
    
    @Override
    public BranchProfile getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<BranchProfile> getAllBranches() {
        return branchRepository.findAll();
    }
    
    @Override
    public BranchProfile updateBranch(Long id, BranchProfile branchDetails) {
        BranchProfile branch = branchRepository.findById(id).orElse(null);
        if (branch != null) {
            branch.setBranchName(branchDetails.getBranchName());
            branch.setBranchCode(branchDetails.getBranchCode());
            branch.setDepartment(branchDetails.getDepartment());
            branch.setContactEmail(branchDetails.getContactEmail());
            branch.setContactPhone(branchDetails.getContactPhone());
            branch.setAddress(branchDetails.getAddress());
            branch.setActive(branchDetails.isActive());
            return branchRepository.save(branch);
        }
        return null;
    }
    
    @Override
    public boolean deleteBranch(Long id) {
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}