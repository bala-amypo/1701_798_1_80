package com.example.demo.controller;

import com.example.demo.entity.BranchProfile;
import com.example.demo.service.BranchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchProfileController {
    
    @Autowired
    private BranchProfileService branchProfileService;
    
    @PostMapping
    public ResponseEntity<BranchProfile> createBranch(@RequestBody BranchProfile branchProfile) {
        BranchProfile createdBranch = branchProfileService.createBranch(branchProfile);
        return ResponseEntity.ok(createdBranch);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BranchProfile> getBranchById(@PathVariable Long id) {
        BranchProfile branchProfile = branchProfileService.getBranchById(id);
        if (branchProfile != null) {
            return ResponseEntity.ok(branchProfile);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<BranchProfile>> getAllBranches() {
        List<BranchProfile> branches = branchProfileService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BranchProfile> updateBranch(@PathVariable Long id, @RequestBody BranchProfile branchDetails) {
        BranchProfile updatedBranch = branchProfileService.updateBranch(id, branchDetails);
        if (updatedBranch != null) {
            return ResponseEntity.ok(updatedBranch);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        boolean deleted = branchProfileService.deleteBranch(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}