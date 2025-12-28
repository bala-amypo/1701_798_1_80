// package com.example.demo.service.impl;

// import com.example.demo.entity.UserAccount;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.exception.ValidationException;
// import com.example.demo.repository.UserAccountRepository;
// import com.example.demo.service.UserAccountService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;

// @Service
// @Transactional
// public class UserAccountServiceImpl implements UserAccountService {
    
//     private final UserAccountRepository userAccountRepository;
//     private final PasswordEncoder passwordEncoder;
    
//     @Autowired
//     public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
//         this.userAccountRepository = userAccountRepository;
//         this.passwordEncoder = passwordEncoder;
//     }
    
//     @Override
//     public UserAccount register(UserAccount user) {
//         if (user.getPassword().length() < 8) {
//             throw new ValidationException("Password must be at least 8 characters");
//         }
        
//         if (userAccountRepository.existsByEmail(user.getEmail())) {
//             throw new ValidationException("Email already in use");
//         }
        
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
        
//         if (user.getRole() == null) {
//             user.setRole("REVIEWER");
//         }
        
//         return userAccountRepository.save(user);
//     }
    
//     @Override
//     public UserAccount getUser(Long id) {
//         return userAccountRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//     }
    
//     @Override
//     public List<UserAccount> getAllUsers() {
//         return userAccountRepository.findAll();
//     }
    
//     @Override
//     public Optional<UserAccount> findByEmail(String email) {
//         return userAccountRepository.findByEmail(email);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
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
        
        user.setCreatedAt(LocalDateTime.now());
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount getUser(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserAccount authenticate(String email, String password) {
        return userAccountRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new ValidationException("Invalid credentials"));
    }
}
