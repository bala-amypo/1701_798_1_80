package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    List<UserAccount> findByActive(Boolean active);
    
    List<UserAccount> findByRole(String role);
    
    List<UserAccount> findByBranchId(Long branchId);
    
    Optional<UserAccount> findByUsername(String username);
    
    Optional<UserAccount> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}