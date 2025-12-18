// In HarmonizedCalendarController.java
@PostMapping("/generate")
public ResponseEntity<HarmonizedCalendar> generateHarmonizedCalendar(
        @RequestParam String userId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        @RequestParam String timeZone) {
    
    HarmonizedCalendar calendar = calendarService.generateHarmonizedCalendar(
        userId, startDate, endDate, timeZone
    );
    return ResponseEntity.ok(calendar);
}

// Remove or fix the problematic method call
@GetMapping("/range")
public ResponseEntity<List<HarmonizedCalendar>> getCalendarsWithinRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    
    // First, check if this method exists in the service interface
    // If not, you need to add it to HarmonizedCalendarService interface
    List<HarmonizedCalendar> calendars = calendarService.getCalendarsWithinRange(startDate, endDate);
    return ResponseEntity.ok(calendars);
}