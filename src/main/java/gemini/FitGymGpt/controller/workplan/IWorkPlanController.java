package gemini.FitGymGpt.controller.workplan;


import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "Gerenciamento de Plano de Treino", description = "Aqui estão os end points para gerenciamento de plano de treino")
public interface IWorkPlanController {

    @Operation(summary = "Gera um plano de treino para o usuário")
    @ApiResponses ({
         @ApiResponse(responseCode = "200", description = "Plano de treino gerado com sucesso"),
         @ApiResponse(responseCode = "400", description = "Erro ao gerar plano de treino")
    })
    @PreAuthorize("hasAnyRole('CEO_TRAINER', 'PERSONAL_TRAINER', 'ROOT')")
    @PostMapping("/generate/{userId}/{gymId}")
    ResponseEntity<?> createWorkPlan(@PathVariable UUID userId, @PathVariable Long gymId, @RequestBody BodyStatsRequest request, @AuthenticationPrincipal UserEntity trainer);

    @Operation(summary = "Recuperar o plano de treino do usuário")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Plano de treino recuperado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao recuperar plano de treino")
    })
    @GetMapping("/my")
    ResponseEntity<?> getMyWorkPlans(@AuthenticationPrincipal UserEntity userEntity);
}
