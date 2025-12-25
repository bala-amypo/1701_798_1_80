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
    public UserAccount createUser(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
    
    @Override
    public UserAccount getUserById(Long id) {
        return userAccountRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }
    
    @Override
    public UserAccount updateUser(Long id, UserAccount userDetails) {
        UserAccount user = userAccountRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setRole(userDetails.getRole());
            user.setBranchId(userDetails.getBranchId());
            user.setActive(userDetails.getActive());
            return userAccountRepository.save(user);
        }
        return null;
    }
    
    @Override
    public boolean deleteUser(Long id) {
        if (userAccountRepository.existsById(id)) {
            userAccountRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Override
    public UserAccount getUserByUsername(String username) {
        Optional<UserAccount> user = userAccountRepository.findByUsername(username);
        return user.orElse(null);
    }
    
    @Override
    public List<UserAccount> getUsersByRole(String role) {
        return userAccountRepository.findByRole(role);
    }
    
    @Override
    public List<UserAccount> getUsersByBranchId(Long branchId) {
        return userAccountRepository.findByBranchId(branchId);
    }
}