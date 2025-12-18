// src/main/java/com/example/demo/service/impl/UserAccountServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    
    private final UserAccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserAccountServiceImpl(UserAccountRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserAccount register(UserAccount user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already registered: " + user.getEmail());
        }
        
        if (!isValidRole(user.getRole())) {
            throw new ValidationException("Invalid role. Must be one of: ADMIN, CALENDAR_MANAGER, REVIEWER");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public UserAccount getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
    
    @Override
    public List<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public UserAccount findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
    
    private boolean isValidRole(String role) {
        return role != null && 
               (role.equals("ADMIN") || 
                role.equals("CALENDAR_MANAGER") || 
                role.equals("REVIEWER"));
    }
}