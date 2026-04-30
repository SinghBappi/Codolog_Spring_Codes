package in.example.Trace.repository;

import in.example.Trace.model.UniqueUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UniqueUser, UUID> { }