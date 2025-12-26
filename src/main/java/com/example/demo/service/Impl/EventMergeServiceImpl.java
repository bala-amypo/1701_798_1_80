@Service
public class EventMergeServiceImpl implements EventMergeService {

    private final AcademicEventRepository eventRepo;
    private final EventMergeRecordRepository mergeRepo;

    public EventMergeServiceImpl(AcademicEventRepository e,
                                 EventMergeRecordRepository m) {
        this.eventRepo = e;
        this.mergeRepo = m;
    }

    public EventMergeRecord mergeEvents(List<Long> ids, String reason) {
        List<AcademicEvent> events = eventRepo.findAllById(ids);
        if (events.isEmpty())
            throw new ResourceNotFoundException("No events found");

        EventMergeRecord mr = new EventMergeRecord();
        mr.setSourceEventIds(
                events.stream().map(e -> e.getId().toString())
                        .collect(Collectors.joining(",")));
        mr.setMergeReason(reason);

        return mergeRepo.save(mr);
    }
}
