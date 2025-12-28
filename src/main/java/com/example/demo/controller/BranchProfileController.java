package com.example.demo.controller;

import com.example.demo.entity.BranchProfile;
import com.example.demo.service.BranchProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@SecurityRequirement(name = "bearerAuth")
public class BranchProfileController {

    private final BranchProfileService branchService;

    public BranchProfileController(BranchProfileService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<BranchProfile> createBranch(@RequestBody BranchProfile branch) {
        return ResponseEntity.ok(branchService.createBranch(branch));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchProfile> getBranch(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranch(id));
    }

    @GetMapping
    public ResponseEntity<List<BranchProfile>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchProfile> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchProfile branch) {
        return ResponseEntity.ok(branchService.updateBranch(id, branch));
    }
}
