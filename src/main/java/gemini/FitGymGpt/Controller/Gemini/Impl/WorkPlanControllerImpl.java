package gemini.FitGymGpt.Controller.Gemini.Impl;

import gemini.FitGymGpt.DTO.Gemini.BodyStatsRequest;
import gemini.FitGymGpt.DataBase.Model.User;
import gemini.FitGymGpt.DataBase.Model.WorkPlan;
import gemini.FitGymGpt.DataBase.Repository.UserRepository;
import gemini.FitGymGpt.DataBase.Repository.WorkPlanRepository;
import gemini.FitGymGpt.Service.Gemini.GeminiService;
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
