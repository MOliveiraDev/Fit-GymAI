package gemini.FitGymGpt.service.workplan;

import gemini.FitGymGpt.database.model.User;
import gemini.FitGymGpt.database.model.WorkPlan;
import gemini.FitGymGpt.database.repository.UserRepository;
import gemini.FitGymGpt.database.repository.WorkPlanRepository;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String jsonPlan = geminiService.workPlanGenerate(request);

        WorkPlan plan = WorkPlan.builder()
                .jsonPlan(jsonPlan)
                .user(user)
                .build();

        WorkPlan saved = workPlanRepository.save(plan);
        return saved.getJsonPlan();
    }
}
