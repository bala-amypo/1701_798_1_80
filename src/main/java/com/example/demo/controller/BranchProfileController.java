// package com.example.demo.controller;

// import com.example.demo.entity.BranchProfile;
// import com.example.demo.service.BranchProfileService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/branches")
// @Tag(name = "Branch Profiles", description = "Branch profile management APIs")
// public class BranchProfileController {
    
//     @Autowired
//     private BranchProfileService branchProfileService;
    
//     @PostMapping
//     @PreAuthorize("hasRole('ADMIN')")
//     @Operation(summary = "Create a new branch")
//     public ResponseEntity<BranchProfile> createBranch(@RequestBody BranchProfile branch) {
//         BranchProfile created = branchProfileService.createBranch(branch);
//         return ResponseEntity.ok(created);
//     }
    
//     @PutMapping("/{id}/status")
//     @PreAuthorize("hasRole('ADMIN')")
//     @Operation(summary = "Activate/deactivate branch")
//     public ResponseEntity<BranchProfile> updateBranchStatus(@PathVariable Long id, @RequestParam boolean active) {
//         BranchProfile updated = branchProfileService.updateBranchStatus(id, active);
//         return ResponseEntity.ok(updated);
//     }
    
//     @GetMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "Get branch by ID")
//     public ResponseEntity<BranchProfile> getBranchById(@PathVariable Long id) {
//         BranchProfile branch = branchProfileService.getBranchById(id);
//         return ResponseEntity.ok(branch);
//     }
    
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "List all branches")
//     public ResponseEntity<List<BranchProfile>> getAllBranches() {
//         List<BranchProfile> branches = branchProfileService.getAllBranches();
//         return ResponseEntity.ok(branches);
//     }
    
//     @GetMapping("/lookup/{branchCode}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'CALENDAR_MANAGER', 'REVIEWER')")
//     @Operation(summary = "Find by branch code")
//     public ResponseEntity<BranchProfile> findByBranchCode(@PathVariable String branchCode) {
//         Optional<BranchProfile> branch = branchProfileService.findByBranchCode(branchCode);
//         return branch.map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }
// }
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
