package in.example.Trace.repository;

import in.example.Trace.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Long> { }
