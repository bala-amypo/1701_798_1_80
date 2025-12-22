package com.example.demo.controller;

import com.example.demo.entity.BranchProfile;
import com.example.demo.service.BranchProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@Tag(name = "Branch Profiles", description = "Branch management endpoints")
public class BranchProfileController {
    
    private final BranchProfileService branchService;
    
    public BranchProfileController(BranchProfileService branchService) {
        this.branchService = branchService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new branch", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<BranchProfile> createBranch(@Valid @RequestBody BranchProfile branch) {
        BranchProfile createdBranch = branchService.createBranch(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBranch);
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Activate/deactivate branch", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<BranchProfile> updateBranchStatus(
            @PathVariable Long id, 
            @RequestParam boolean active) {
        BranchProfile updatedBranch = branchService.updateBranchStatus(id, active);
        return ResponseEntity.ok(updatedBranch);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get branch by ID", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<BranchProfile> getBranchById(@PathVariable Long id) {
        BranchProfile branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }
    
    @GetMapping
    @Operation(summary = "List all branches", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<BranchProfile>> getAllBranches() {
        List<BranchProfile> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    
    @GetMapping("/lookup/{branchCode}")
    @Operation(summary = "Find branch by branch code", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<BranchProfile> findByBranchCode(@PathVariable String branchCode) {
        BranchProfile branch = branchService.findByBranchCode(branchCode);
        return ResponseEntity.ok(branch);
    }
}