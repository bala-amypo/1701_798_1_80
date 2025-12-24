package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    UserAccount createUser(UserAccount user);
    UserAccount getUserById(Long id);
    List<UserAccount> getAllUsers();
    UserAccount updateUser(Long id, UserAccount userDetails);
    void deleteUser(Long id);
    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByEmail(String email);
}