import com.example.demo.dto.MergeEventsRequest;
import com.example.demo.entity.EventMergeRecord;
import com.example.demo.service.EventMergeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/merge-records")
@Tag(name = "Event Merge Records", description = "Event merging endpoints")
public class EventMergeController {
    
    private final EventMergeService mergeService;
    
    public EventMergeController(EventMergeService mergeService) {
        this.mergeService = mergeService;
    }
    
    @PostMapping
    @Operation(summary = "Merge multiple events", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<EventMergeRecord> mergeEvents(@RequestBody MergeEventsRequest request) {
        EventMergeRecord mergeRecord = mergeService.mergeEvents(
            request.getEventIds(), 
            request.getReason()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mergeRecord);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get merge record by ID", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<EventMergeRecord> getMergeRecordById(@PathVariable Long id) {
        EventMergeRecord mergeRecord = mergeService.getMergeRecordById(id);
        return ResponseEntity.ok(mergeRecord);
    }
    
    @GetMapping
    @Operation(summary = "List all merges", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<EventMergeRecord>> getAllMergeRecords() {
        List<EventMergeRecord> mergeRecords = mergeService.getAllMergeRecords();
        return ResponseEntity.ok(mergeRecords);
    }
    
    @GetMapping("/range")
    @Operation(summary = "Get merges by date range", 
               security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<EventMergeRecord>> getMergeRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<EventMergeRecord> mergeRecords = mergeService.getMergeRecordsByDate(start, end);
        return ResponseEntity.ok(mergeRecords);
    }
}