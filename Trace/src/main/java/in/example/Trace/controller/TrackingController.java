package in.example.Trace.controller;

import in.example.Trace.dto.TrackingPayload;
import in.example.Trace.model.UserActivityLog;
import in.example.Trace.repository.UserActivityLogRepository;
import in.example.Trace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/track")
@CrossOrigin(origins = "*")
public class TrackingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserActivityLogRepository activityLogRepository;

    @PostMapping("/event")
    public ResponseEntity<?> trackEvent(@RequestBody TrackingPayload payload) {

        if (payload.userId == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Missing User ID. Tracking data rejected.");
        }

        if (!userRepository.existsById(payload.userId)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error: Invalid User ID. User does not exist.");
        }

        UserActivityLog log = new UserActivityLog();
        log.setUserId(payload.userId);
        log.setBrowser(payload.browser);
        log.setScreenSize(payload.screenSize);
        log.setUrl(payload.url);

        log.setAccessTime(LocalDateTime.now());

        activityLogRepository.save(log);

        return ResponseEntity.ok("Activity logged successfully for user: " + payload.userId);
    }
}