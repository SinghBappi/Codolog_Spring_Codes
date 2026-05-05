package in.example.Trace.repository;

import in.example.Trace.model.UserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, Long> {
}
//localStorage.setItem('codolog_user_id', 'ea2b5b8a-1bf8-419a-a075-4aa1a103c040');