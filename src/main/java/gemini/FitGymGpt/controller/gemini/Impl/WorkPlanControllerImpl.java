package gemini.FitGymGpt.controller.gemini.Impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.database.model.User;
import gemini.FitGymGpt.database.model.WorkPlan;
import gemini.FitGymGpt.database.repository.UserRepository;
import gemini.FitGymGpt.database.repository.WorkPlanRepository;
import gemini.FitGymGpt.service.gemini.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workplan")
public class WorkPlanControllerImpl {

    private final GeminiService geminiService;
    private final UserRepository userRepository;
    private final WorkPlanRepository workPlanRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate/{userId}")
    public ResponseEntity<?> createWorkPlan(@PathVariable Long userId, @RequestBody BodyStatsRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String jsonPlan = geminiService.workPlanGenerate(request);

        WorkPlan plan = WorkPlan.builder()
                .jsonPlan(jsonPlan)
                .user(user)
                .build();

        WorkPlan saved = workPlanRepository.save(plan);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyWorkPlans(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(workPlanRepository.findByUser(user));
    }
}
