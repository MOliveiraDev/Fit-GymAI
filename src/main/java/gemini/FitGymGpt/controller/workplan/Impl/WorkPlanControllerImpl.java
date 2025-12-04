package gemini.FitGymGpt.controller.workplan.Impl;

import gemini.FitGymGpt.controller.workplan.IWorkPlanController;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.database.repository.workplan.WorkPlanRepository;
import gemini.FitGymGpt.service.workplan.WorkPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workplan")
public class WorkPlanControllerImpl implements IWorkPlanController {

    private final WorkPlanRepository workPlanRepository;
    private final WorkPlanService workPlanService;

    @Override
    public ResponseEntity<?> createWorkPlan(@PathVariable UUID userId, @PathVariable Long gymId, @RequestBody BodyStatsRequest request, @AuthenticationPrincipal UserEntity trainer) {
        String jsonPlan = workPlanService.generateWorkPlan(userId, gymId, request, trainer);
        return ResponseEntity.ok(jsonPlan);
    }

    @Override
    public ResponseEntity<?> getMyWorkPlans(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(workPlanRepository.findByUserEntity(userEntity));
    }
}
