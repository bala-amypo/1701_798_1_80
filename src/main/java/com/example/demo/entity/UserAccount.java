// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "user_accounts")
// public class UserAccount {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(nullable = false)
//     private String fullName;
    
//     @Column(unique = true, nullable = false)
//     private String email;
    
//     @Column(nullable = false)
//     private String password;
    
//     private String role;
    
//     private String department;
    
//     private LocalDateTime createdAt;
    
//     // Constructors
//     public UserAccount() {
//         this.role = "REVIEWER";
//     }
    
//     public UserAccount(Long id, String fullName, String email, String password, 
//                       String role, String department, LocalDateTime createdAt) {
//         this.id = id;
//         this.fullName = fullName;
//         this.email = email;
//         this.password = password;
//         this.role = role != null ? role : "REVIEWER";
//         this.department = department;
//         this.createdAt = createdAt;
//     }
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getFullName() { return fullName; }
//     public void setFullName(String fullName) { this.fullName = fullName; }
    
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
    
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
    
//     public String getRole() { return role; }
//     public void setRole(String role) { this.role = role; }
    
//     public String getDepartment() { return department; }
//     public void setDepartment(String department) { this.department = department; }
    
//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
//     @PrePersist
//     public void prePersist() {
//         if (createdAt == null) {
//             createdAt = LocalDateTime.now();
//         }
//         if (role == null) {
//             role = "REVIEWER";
//         }
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // This field is REQUIRED by your database (NOT NULL)
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    // This is what your API sends as "fullName"
    @Column(name = "full_name")
    private String fullName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private String role;
    
    private String department;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    // This method runs BEFORE saving to database
    @PrePersist
    @PreUpdate
    private void prepareData() {
        // If firstName is empty/null but fullName exists, extract firstName from fullName
        if ((firstName == null || firstName.isEmpty()) && 
            fullName != null && !fullName.isEmpty()) {
            String[] names = fullName.split(" ", 2);
            this.firstName = names[0];
        }
        
        // If fullName is empty/null but firstName exists, set fullName = firstName
        if ((fullName == null || fullName.isEmpty()) && 
            firstName != null && !firstName.isEmpty()) {
            this.fullName = this.firstName;
        }
    }
    
    // Optional: Add this constructor for easier object creation
    public UserAccount(String fullName, String email, String password, 
                      String role, String department) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
        // firstName will be auto-set by @PrePersist
    }
}