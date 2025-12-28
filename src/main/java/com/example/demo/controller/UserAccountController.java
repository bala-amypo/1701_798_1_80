// package com.example.demo.controller;

// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.ApiResponse;
// import com.example.demo.entity.UserAccount;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.UserAccountService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/users")
// public class UserAccountController {

//     @Autowired
//     private UserAccountService userAccountService;

//     @Autowired
//     private JwtUtil jwtUtil;

//     @PostMapping("/register")
//     public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) {
//         // Map DTO to entity
//         UserAccount user = new UserAccount();
//         user.setFullName(request.getFullName());
//         user.setEmail(request.getEmail());
//         user.setPassword(request.getPassword());
//         user.setRole(request.getRole());
//         user.setDepartment(request.getDepartment());

//         // Save user
//         UserAccount savedUser = userAccountService.registerUser(user);

//         // Generate JWT
//         String token = jwtUtil.generateToken(savedUser);

//         // Build response
//         AuthResponse response = new AuthResponse(token, savedUser.getEmail(), savedUser.getRole());
//         return ResponseEntity.ok(response);
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request) {
//         UserAccount user = userAccountService.authenticate(request.getEmail(), request.getPassword());
//         String token = jwtUtil.generateToken(user);
//         AuthResponse response = new AuthResponse(token, user.getEmail(), user.getRole());
//         return ResponseEntity.ok(response);
//     }

//     @GetMapping("/status")
//     public ResponseEntity<ApiResponse> status() {
//         return ResponseEntity.ok(new ApiResponse(true, "Service is running"));
//     }
// }

package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;

import com.example.demo.service.UserAccountService;
import com.example.demo.security.JwtUtil;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    @Autowired
        private UserAccountService userAccountService;

            @Autowired
                private JwtUtil jwtUtil;
                    
                        @PostMapping("/register")
                            public UserAccount registerUser(@RequestBody UserAccount user) {
                                    return userAccountService.register(user);
                                        }
                                        //     @PostMapping("/register")
                                        //     public ResponseEntity<AuthResponse> registerUser(
                                        //             @RequestBody RegisterRequest request) {

                                        //         UserAccount user = new UserAccount();
                                        //         user.setFullName(request.getFullName());
                                        //         user.setEmail(request.getEmail());
                                        //         user.setPassword(request.getPassword());
                                        //         user.setRole(request.getRole());
                                        //         user.setDepartment(request.getDepartment());

                                        //         UserAccount savedUser = userAccountService.registerUser(user);
                                        //         String email = request.getEmail();
                                        //         // ✅ FIX IS HERE
                                        //         String token = jwtUtil.generateToken(
                                        //         Map.of("email", email),
                                        //         email
                                        // );


                                        //         AuthResponse response = new AuthResponse(
                                        //                 token,
                                        //                 savedUser.getEmail(),
                                        //                 savedUser.getRole()
                                        //         );

                                        //         return ResponseEntity.ok(response);
                                        //     }

                                            @PostMapping("/login")
                                                public ResponseEntity<AuthResponse> loginUser(
                                                            @RequestBody LoginRequest request) {

                                                                    UserAccount user = userAccountService.authenticate(
                                                                                    request.getEmail(),
                                                                                                    request.getPassword()
                                                                                                            );
                                                                                                                    String email = request.getEmail();
                                                                                                                            // ✅ FIX IS HERE
                                                                                                                                    String token = jwtUtil.generateToken(
                                                                                                                                            Map.of(
                                                                                                                                                            "userId", user.getId(),
                                                                                                                                                                            "email", user.getEmail(),
                                                                                                                                                                                            "role", user.getRole()
                                                                                                                                                                                                    ),
                                                                                                                                                                                                            user.getEmail()
                                                                                                                                                                                                            );


                                                                                                                                                                                                                    AuthResponse response = new AuthResponse(
                                                                                                                                                                                                                                    token,
                                                                                                                                                                                                                                                    user.getEmail(),
                                                                                                                                                                                                                                                                    user.getRole()
                                                                                                                                                                                                                                                                            );

                                                                                                                                                                                                                                                                                    return ResponseEntity.ok(response);
                                                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                                                            @GetMapping("/status")
                                                                                                                                                                                                                                                                                                public ResponseEntity<ApiResponse> status() {
                                                                                                                                                                                                                                                                                                        return ResponseEntity.ok(
                                                                                                                                                                                                                                                                                                                        new ApiResponse(true, "Service is running")
                                                                                                                                                                                                                                                                                                                                );
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                    

