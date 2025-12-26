package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository repo, PasswordEncoder encoder) {
        this.userAccountRepository = repo;
        this.passwordEncoder = encoder;
    }

    public UserAccount register(UserAccount user) {
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        if (user.getRole() == null) {
            user.setRole("REVIEWER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.prePersist();
        return userAccountRepository.save(user);
    }

    public UserAccount getUser(Long id) {
        Optional<UserAccount> opt = userAccountRepository.findById(id);
        return opt.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
