package gemini.FitGymGpt.service.workplan;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.database.domain.workplan.WorkPlan;
import gemini.FitGymGpt.database.repository.fitcenter.GymCenterRepository;
import gemini.FitGymGpt.database.repository.user.UserRepository;
import gemini.FitGymGpt.database.repository.workplan.WorkPlanRepository;
import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.service.gemini.GeminiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkPlanService {

    private final UserRepository userRepository;
    private final GeminiService geminiService;
    private final GymCenterRepository gymCenterRepository;
    private final WorkPlanRepository workPlanRepository;

    @Transactional
    public String generateWorkPlan(UUID userId, Long gymCenterId, BodyStatsRequest request, UserEntity trainer) {
        UserEntity userEntity = (UserEntity) userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        GymCenterEntity gymCenter = gymCenterRepository.findById(gymCenterId)
                .orElseThrow(() -> new RuntimeException("Academia não encontrada"));

        if (userEntity.getGymCenterEntity() == null) {
            userEntity.setGymCenterEntity(gymCenter);
            userRepository.save(userEntity);
        }

        String jsonPlan = geminiService.generateWorkoutPlan(request);

        WorkPlan plan = new WorkPlan();
        plan.setJsonPlan(jsonPlan);
        plan.setUserEntity(userEntity);

        WorkPlan saved = workPlanRepository.save(plan);
        return saved.getJsonPlan();
    }
}
