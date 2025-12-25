package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_records")
@Data  // 自动生成 getter、setter、toString 等方法
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "event_id1")
    private Long eventId1;
    
    @Column(name = "event_id2")
    private Long eventId2;
    
    @Column(name = "clash_type")
    private String clashType;
    
    @Column(name = "severity")
    private String severity;
    
    @Column(name = "detected_at")
    private LocalDateTime detectedAt;
    
    @Column(name = "resolved")
    private boolean resolved = false;
    
    @Column(name = "resolution")
    private String resolution;
}