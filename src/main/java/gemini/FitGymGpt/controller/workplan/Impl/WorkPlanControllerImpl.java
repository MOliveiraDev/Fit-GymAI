package gemini.FitGymGpt.controller.workplan.Impl;

import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.database.repository.workplan.WorkPlanRepository;
import gemini.FitGymGpt.service.workplan.WorkPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workplan")
public class WorkPlanControllerImpl {

    private final WorkPlanRepository workPlanRepository;
    private final WorkPlanService workPlanService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate/{userId}")
    public ResponseEntity<?> createWorkPlan(@PathVariable Long userId, @RequestBody BodyStatsRequest request) {
        String jsonPlan = workPlanService.generateWorkPlan(userId, request);
        return ResponseEntity.ok(jsonPlan);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyWorkPlans(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(workPlanRepository.findByUserEntity(userEntity));
    }
}
