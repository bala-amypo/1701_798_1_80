package com.example.demo.controller;

import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user")
public class UserAccountController {

    private final HarmonizedCalendarService calendarService;

    public UserAccountController(HarmonizedCalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/calendar")
    public String createCalendar() {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken("user", null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        calendarService.generateHarmonizedCalendar(
                "My Calendar",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "SYSTEM"
        );

        return "Calendar created successfully";
    }
}
