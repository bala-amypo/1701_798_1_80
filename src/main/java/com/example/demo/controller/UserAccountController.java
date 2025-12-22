package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserAccountController {
    
    private final UserAccountService userService;
    private final PasswordEncoder passwordEncoder;
    
    public UserAccountController(UserAccountService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        UserAccount registeredUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        
        UserAccount user = userService.findByEmail(email);
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", "dummy-jwt-token-for-" + email);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("name", user.getFullName());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        List<UserAccount> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<UserAccount> getUserById(@PathVariable Long id) {
        UserAccount user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
}