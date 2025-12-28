// package com.example.demo.repository;

// import com.example.demo.entity.BranchProfile;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import java.util.Optional;

// @Repository
// public interface BranchProfileRepository extends JpaRepository<BranchProfile, Long> {
//     Optional<BranchProfile> findByBranchCode(String branchCode);
// }

package com.example.demo.repository;

import com.example.demo.entity.BranchProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchProfileRepository extends JpaRepository<BranchProfile, Long> {
    
    boolean existsByBranchCode(String branchCode);
}
