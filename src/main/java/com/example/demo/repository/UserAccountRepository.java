package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    // FIX 1: Remove or fix the findByIsActive method
    // Option A: Remove it completely if you don't need it
    // Option B: Change it to match your UserAccount entity property
    
    // If your UserAccount entity has property named 'active' (without 'is' prefix):
    List<UserAccount> findByActive(Boolean active);
    
    // OR if your UserAccount entity has property named 'status':
    // List<UserAccount> findByStatus(String status);
    
    // OR if you need to keep the method name 'findByIsActive', use @Query:
    /*
    @Query("SELECT u FROM UserAccount u WHERE u.active = :isActive")
    List<UserAccount> findByIsActive(@Param("isActive") Boolean isActive);
    */
    
    // Other repository methods that should exist:
    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    // If your code references these methods, add them:
    List<UserAccount> findByRole(String role);  // If UserAccount has 'role' property
    List<UserAccount> findByBranchId(Long branchId);  // If UserAccount has 'branchId' property
}   