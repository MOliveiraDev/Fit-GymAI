package gemini.FitGymGpt.DataBase.Repository;

import gemini.FitGymGpt.DataBase.Model.User;
import gemini.FitGymGpt.DataBase.Model.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPlanRepository extends JpaRepository<WorkPlan, Long> {
    List<WorkPlan> findByUser(User user);
}
