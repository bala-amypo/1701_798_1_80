// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// public class BranchProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Boolean active;
// }
@Entity
public class BranchProfile {

    @Id @GeneratedValue
    private Long id;
    private String branchCode;
    private String branchName;
    private String contactEmail;
    private LocalDateTime lastSyncAt;
    private Boolean active;

    @PrePersist
    public void prePersist() {
        lastSyncAt = LocalDateTime.now();
        if (active == null) active = true;
    }
}

