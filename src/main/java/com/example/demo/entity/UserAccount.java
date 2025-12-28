// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "user_accounts")
// public class UserAccount {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(unique = true, nullable = false)
//     private String email;

//     private String password;

//     private String role;

//     private String department;

//     private LocalDateTime createdAt;

//     public UserAccount() {
//     }

//     public UserAccount(Long id, String fullName, String email, String password,
//                        String role, String department, LocalDateTime createdAt) {
//         this.id = id;
//         this.fullName = fullName;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//         this.department = department;
//         this.createdAt = createdAt;
//     }

//     @PrePersist
//     public void prePersist() {
//         this.createdAt = LocalDateTime.now();
//         if (this.role == null) {
//             this.role = "REVIEWER";
//         }
//     }

//     // Getters and Setters

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }
    
//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getRole() {
//         return role;
//     }
    
//     public void setRole(String role) {
//         this.role = role;
//     }

//     public String getDepartment() {
//         return department;
//     }
    
//     public void setDepartment(String department) {
//         this.department = department;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime t){ this.createdAt = t; }

// }

package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("callUser")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role;

    private String department;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    private LocalDateTime createdAt;

    // Constructors...

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.role == null) {
            this.role = "REVIEWER";
        }
        if (this.department == null) {
            this.department = "IT";
        }
    }

    // GETTERS AND SETTERS - MAKE SURE ALL FIELDS HAVE THEM!
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getCode() { return code; }  // ← YOU'RE MISSING THIS!
    public void setCode(String code) { this.code = code; }  // ← AND THIS!
    
    public String getDescription() { return description; }  // ← AND THIS!
    public void setDescription(String description) { this.description = description; }  // ← AND THIS!
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}