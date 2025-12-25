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
    
    // FIX 1: Use the correct property name (most common solution)
    // If your entity has property 'active', use:
    List<UserAccount> findByActive(Boolean active);
    
    // FIX 2: Or use a custom query if you prefer to keep the method name:
    /*
    @Query("SELECT u FROM UserAccount u WHERE u.active = :isActive")
    List<UserAccount> findByIsActive(@Param("isActive") Boolean isActive);
    */
    
    // Other repository methods you might have:
    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}