package com.example.demo.service.impl;

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
    public UserAccount createUser(UserAccount user) {
        return register(user); // Use register method
    }
    
    @Override
    public UserAccount register(UserAccount user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        
        return userRepository.save(user);
    }
    
    @Override
    public UserAccount getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    @Override
    public UserAccount getUser(Long id) {
        return getUserById(id); // Alias for getUserById
    }
    
    @Override
    public List<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public UserAccount updateUser(Long id, UserAccount userDetails) {
        UserAccount user = getUserById(id);
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        
        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(Long id) {
        UserAccount user = getUserById(id);
        userRepository.delete(user);
    }
    
    @Override
    public UserAccount findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
    
    @Override
    public UserAccount findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}   