package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;

public interface UserAccountService {
    UserAccount createUser(UserAccount user);
    UserAccount register(UserAccount user);  // Add this method
    UserAccount getUserById(Long id);
    UserAccount getUser(Long id);  // Add this method (or use getUserById)
    List<UserAccount> getAllUsers();
    UserAccount updateUser(Long id, UserAccount userDetails);
    void deleteUser(Long id);
    UserAccount findByUsername(String username);  // Change from Optional<UserAccount>
    UserAccount findByEmail(String email);  // Change from Optional<UserAccount>
}