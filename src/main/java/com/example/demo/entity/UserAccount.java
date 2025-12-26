// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// public class UserAccount {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String email;
//     private String password;
//     private String role;

//     // PrePersist method called by UserAccountServiceImpl
//     @PrePersist
//     public void prePersist() {
//         // Optional: Can leave empty or initialize default values
//     }
// }

package com.example.demo.entity;

@Entity
public class UserAccount {

    @Id @GeneratedValue
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String department;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (role == null) role = "REVIEWER";
    }
}
