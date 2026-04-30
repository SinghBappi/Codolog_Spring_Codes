package in.example.Trace.controller;

import in.example.Trace.dto.TrackingPayload;
import in.example.Trace.model.Observation;
import in.example.Trace.model.UniqueUser;
import in.example.Trace.repository.ObservationRepository;
import in.example.Trace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/track")
@CrossOrigin(origins = "*")
public class TrackingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObservationRepository observationRepository;

    @PostMapping("/event")
    public String trackEvent(@RequestBody TrackingPayload payload) {
        if (!userRepository.existsById(payload.userId)) {
            UniqueUser newUser = new UniqueUser();
            newUser.setUserId(payload.userId);
            newUser.setDeviceType(payload.deviceType);
            newUser.setScreenSize(payload.screenSize);
            newUser.setBrowser(payload.browser);
            newUser.setFirstVisitTimestamp(LocalDateTime.now());
            userRepository.save(newUser);
        }

        Observation obs = new Observation();
        obs.setUserId(payload.userId);
        obs.setUrl(payload.url);
        obs.setActionEvent(payload.action);
        obs.setEventTimestamp(LocalDateTime.now());
        observationRepository.save(obs);

        return "Event tracked successfully";
    }
}
