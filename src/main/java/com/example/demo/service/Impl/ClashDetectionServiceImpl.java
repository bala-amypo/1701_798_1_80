@Service
public class ClashDetectionServiceImpl implements ClashDetectionService {

    private final ClashRecordRepository repo;

    public ClashDetectionServiceImpl(ClashRecordRepository repo) {
        this.repo = repo;
    }

    public ClashRecord resolveClash(Long id) {
        ClashRecord cr = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clash not found"));
        cr.setResolved(true);
        return repo.save(cr);
    }

    public List<ClashRecord> getClashesForEvent(Long id) {
        return repo.findByEventAIdOrEventBId(id, id);
    }
}
