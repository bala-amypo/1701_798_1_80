package com.example.demo.service;

import com.example.demo.entity.HarmonizedCalendar;

import java.time.LocalDate;
import java.util.List;

public interface HarmonizedCalendarService {

    /**
     * Generate a harmonized calendar for a user within a specified date range
     *
     * @param userId The ID of the user
     * @param startDate The start date of the calendar range
     * @param endDate The end date of the calendar range
     * @param timeZone The timezone for the calendar
     * @return The generated harmonized calendar
     */
    HarmonizedCalendar generateHarmonizedCalendar(String userId, LocalDate startDate, LocalDate endDate, String timeZone);

    /**
     * Get all harmonized calendars within a specific date range
     *
     * @param startDate The start date of the range
     * @param endDate The end date of the range
     * @return List of harmonized calendars within the specified range
     */
    List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate startDate, LocalDate endDate);

    /**
     * Get a harmonized calendar by its ID
     *
     * @param id The ID of the harmonized calendar
     * @return The harmonized calendar
     */
    HarmonizedCalendar getHarmonizedCalendarById(String id);

    /**
     * Get all harmonized calendars for a specific user
     *
     * @param userId The ID of the user
     * @return List of harmonized calendars for the user
     */
    List<HarmonizedCalendar> getHarmonizedCalendarsByUserId(String userId);

    /**
     * Delete a harmonized calendar by its ID
     *
     * @param id The ID of the harmonized calendar to delete
     */
    void deleteHarmonizedCalendar(String id);

    /**
     * Update an existing harmonized calendar
     *
     * @param id The ID of the harmonized calendar to update
     * @param updatedCalendar The updated calendar data
     * @return The updated harmonized calendar
     */
    HarmonizedCalendar updateHarmonizedCalendar(String id, HarmonizedCalendar updatedCalendar);
}