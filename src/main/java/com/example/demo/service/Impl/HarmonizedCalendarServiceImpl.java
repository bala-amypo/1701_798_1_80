package com.example.demo.service.impl;

@Service
public class HarmonizedCalendarServiceImpl
        implements HarmonizedCalendarService {

    private final HarmonizedCalendarRepository repo;

    public HarmonizedCalendarServiceImpl(
            HarmonizedCalendarRepository repo) {
        this.repo = repo;
    }

    public HarmonizedCalendar generateHarmonizedCalendar(
            String title, String generatedBy) {

        HarmonizedCalendar cal = new HarmonizedCalendar();
        cal.setTitle(title);
        cal.setGeneratedBy(generatedBy);
        return repo.save(cal);
    }

    public HarmonizedCalendar getCalendarById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Calendar not found"));
    }

    public List<HarmonizedCalendar> getAllCalendars() {
        return repo.findAll();
    }

    public List<HarmonizedCalendar> getCalendarsWithinRange(
            LocalDate start, LocalDate end) {
        return repo
                .findByEffectiveFromLessThanEqualAndEffectiveToGreaterThanEqual(
                        start, end);
    }
}
