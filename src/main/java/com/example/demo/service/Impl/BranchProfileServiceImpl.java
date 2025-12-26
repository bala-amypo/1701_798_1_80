@Service
public class BranchProfileServiceImpl implements BranchProfileService {

    private final BranchProfileRepository repo;

    public BranchProfileServiceImpl(BranchProfileRepository repo) {
        this.repo = repo;
    }

    public BranchProfile createBranch(BranchProfile b) {
        return repo.save(b);
    }

    public BranchProfile updateBranchStatus(Long id, boolean active) {
        BranchProfile bp = getBranchById(id);
        bp.setActive(active);
        return repo.save(bp);
    }

    public List<BranchProfile> getAllBranches() {
        return repo.findAll();
    }

    public BranchProfile getBranchById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch not found"));
    }

    public BranchProfile findByBranchCode(String code) {
        return repo.findByBranchCode(code)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Branch not found"));
    }
}
