// package com.example.demo.service;

// import com.example.demo.entity.UserAccount;
// import java.util.List;
// import java.util.Optional;

// public interface UserAccountService {
//     List<UserAccount> getAllUserAccounts();
//     Optional<UserAccount> getUserAccountById(Long id);
//     UserAccount saveUserAccount(UserAccount userAccount);
//     UserAccount updateUserAccount(Long id, UserAccount userAccount);
//     void deleteUserAccount(Long id);
//     Optional<UserAccount> getUserByUsername(String username);
//     Optional<UserAccount> getUserByEmail(String email);
//     boolean existsByUsername(String username);
//     boolean existsByEmail(String email);
// }
package com.example.demo.service;

import com.example.demo.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount register(UserAccount user);

    UserAccount getUser(Long id);

    List<UserAccount> getAllUsers();

    UserAccount findByEmail(String email);
}
