package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccount register(UserAccount user) {
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }
        if (user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userAccountRepository.save(user);
    }

    public UserAccount getUser(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}