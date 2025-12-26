// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;
// import java.time.LocalDate;

// @Entity
// @Getter
// @Setter
// public class HarmonizedCalendar {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String generatedBy;
//     private LocalDate effectiveFrom;
//     private LocalDate effectiveTo;
//     private String eventsJson;
// }
@Entity
public class HarmonizedCalendar {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String generatedBy;
    private LocalDateTime generatedAt;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String eventsJson;

    @PrePersist
    public void prePersist() {
        generatedAt = LocalDateTime.now();
    }
}
