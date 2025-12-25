package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }
    
    @Override
    public Optional<UserAccount> getUserAccountById(Long id) {
        return userAccountRepository.findById(id);
    }
    
    @Override
    public UserAccount saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
    
    @Override
    public UserAccount updateUserAccount(Long id, UserAccount userAccount) {
        if (userAccountRepository.existsById(id)) {
            userAccount.setId(id);
            return userAccountRepository.save(userAccount);
        }
        return null;
    }
    
    @Override
    public void deleteUserAccount(Long id) {
        userAccountRepository.deleteById(id);
    }
    
    @Override
    public Optional<UserAccount> getUserByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
    
    @Override
    public Optional<UserAccount> getUserByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        return userAccountRepository.existsByUsername(username);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return userAccountRepository.existsByEmail(email);
    }
}