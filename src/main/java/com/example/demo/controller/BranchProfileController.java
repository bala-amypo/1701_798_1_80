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
    private BranchProfileService branchService;
    
    @PostMapping
    public ResponseEntity<BranchProfile> createBranch(@RequestBody BranchProfile branch) {
        BranchProfile createdBranch = branchService.createBranch(branch);
        return ResponseEntity.ok(createdBranch);
    }
    
    @GetMapping
    public ResponseEntity<List<BranchProfile>> getAllBranches() {
        List<BranchProfile> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BranchProfile> getBranchById(@PathVariable Long id) {
        BranchProfile branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }
    
    @GetMapping("/code/{branchCode}")
    public ResponseEntity<BranchProfile> getBranchByCode(@PathVariable String branchCode) {
        BranchProfile branch = branchService.findByBranchCode(branchCode);
        return ResponseEntity.ok(branch);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<BranchProfile> updateBranchStatus(@PathVariable Long id, @RequestParam boolean active) {
        BranchProfile updatedBranch = branchService.updateBranchStatus(id, active);
        return ResponseEntity.ok(updatedBranch);
    }
}