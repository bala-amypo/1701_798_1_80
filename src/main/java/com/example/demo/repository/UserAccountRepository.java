package com.example.demo.repository;

import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    Optional<UserAccount> findByUsername(String username);
    
    List<UserAccount> findByRole(String role);
    
    List<UserAccount> findByBranchId(Long branchId);
    
    List<UserAccount> findByActive(boolean active);
    
    Optional<UserAccount> findByEmail(String email);
}