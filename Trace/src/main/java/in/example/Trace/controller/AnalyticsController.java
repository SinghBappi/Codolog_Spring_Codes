package in.example.Trace.controller;

import in.example.Trace.model.Observation;
import in.example.Trace.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    @Autowired
    private ObservationRepository observationRepository;

    @GetMapping("/events")
    public List<Observation> getAllEvents() {
        return observationRepository.findAll();
    }
}
