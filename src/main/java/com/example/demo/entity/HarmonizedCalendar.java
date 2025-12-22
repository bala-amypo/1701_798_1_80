package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class HarmonizedCalendar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private String timeZone;
    private LocalDateTime generatedAt;
    private Integer totalEvents;
    private Integer uniqueDays;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserAccount getUserAccount() { return userAccount; }
    public void setUserAccount(UserAccount userAccount) { this.userAccount = userAccount; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getTimeZone() { return timeZone; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }
    
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
    
    public Integer getTotalEvents() { return totalEvents; }
    public void setTotalEvents(Integer totalEvents) { this.totalEvents = totalEvents; }
    
    public Integer getUniqueDays() { return uniqueDays; }
    public void setUniqueDays(Integer uniqueDays) { this.uniqueDays = uniqueDays; }
}