package gemini.FitGymGpt.service.workplan;

import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.database.domain.workplan.WorkPlan;
import gemini.FitGymGpt.database.repository.user.UserRepository;
import gemini.FitGymGpt.database.repository.workplan.WorkPlanRepository;
import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.service.gemini.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkPlanService {

    private final UserRepository userRepository;
    private final GeminiService geminiService;
    private final WorkPlanRepository workPlanRepository;

    public String generateWorkPlan(Long userId, BodyStatsRequest request) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String jsonPlan = geminiService.generateWorkoutPlan(request);

        WorkPlan plan = new WorkPlan();
        plan.setJsonPlan(jsonPlan);
        plan.setUserEntity(userEntity);

        WorkPlan saved = workPlanRepository.save(plan);
        return saved.getJsonPlan();
    }
}
