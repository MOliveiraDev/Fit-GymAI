package gemini.FitGymGpt.database.repository.workplan;

import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.database.domain.workplan.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPlanRepository extends JpaRepository<WorkPlan, Long> {

    List<WorkPlan> findByUser(UserEntity userEntity);
}
