package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;

public interface UserAccountService {
    UserAccount createUser(UserAccount user);
    UserAccount register(UserAccount user);
    UserAccount getUserById(Long id);
    UserAccount getUser(Long id);
    List<UserAccount> getAllUsers();
    UserAccount updateUser(Long id, UserAccount userDetails);
    void deleteUser(Long id);
    UserAccount findByUsername(String username);
    UserAccount findByEmail(String email);
}