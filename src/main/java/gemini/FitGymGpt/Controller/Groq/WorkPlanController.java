package gemini.FitGymGpt.Controller.Groq;

import gemini.FitGymGpt.DTO.Groq.BodyStatsRequest;
import gemini.FitGymGpt.DataBase.Model.User;
import gemini.FitGymGpt.DataBase.Model.WorkPlan;
import gemini.FitGymGpt.DataBase.Repository.UserRepository;
import gemini.FitGymGpt.DataBase.Repository.WorkPlanRepository;
import gemini.FitGymGpt.Service.Groq.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workplan")
public class WorkPlanController {

    private final GroqService groqService;
    private final UserRepository userRepository;
    private final WorkPlanRepository workPlanRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate/{userId}")
    public ResponseEntity<?> createWorkPlan(@PathVariable Long userId, @RequestBody BodyStatsRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String jsonPlan = groqService.generateGroqResponse(request);

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
