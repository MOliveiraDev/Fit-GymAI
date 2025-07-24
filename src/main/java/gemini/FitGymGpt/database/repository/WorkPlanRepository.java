package gemini.FitGymGpt.database.repository;

import gemini.FitGymGpt.database.model.User;
import gemini.FitGymGpt.database.model.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPlanRepository extends JpaRepository<WorkPlan, Long> {
    List<WorkPlan> findByUser(User user);
}
