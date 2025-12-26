package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    UserAccount register(UserAccount user);
    UserAccount getUser(Long id);
    List<UserAccount> getAllUsers();
    Optional<UserAccount> findByEmail(String email);
}