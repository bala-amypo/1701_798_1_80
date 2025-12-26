// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// public class ClashRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private boolean resolved;
// }
@Entity
public class ClashRecord {

    @Id @GeneratedValue
    private Long id;
    private Long eventAId;
    private Long eventBId;
    private String clashType;
    private String severity;
    private String details;
    private LocalDateTime detectedAt;
    private Boolean resolved;

    @PrePersist
    public void prePersist() {
        detectedAt = LocalDateTime.now();
        if (resolved == null) resolved = false;
    }
}
