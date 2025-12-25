package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;

public interface UserAccountService {
    UserAccount createUser(UserAccount userAccount);
    UserAccount getUserById(Long id);
    List<UserAccount> getAllUsers();
    UserAccount updateUser(Long id, UserAccount userDetails);
    boolean deleteUser(Long id);
    UserAccount getUserByUsername(String username);
    List<UserAccount> getUsersByRole(String role);
    List<UserAccount> getUsersByBranchId(Long branchId);
}