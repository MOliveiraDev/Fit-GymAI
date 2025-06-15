package gemini.FitGymGpt.Controller.Groq.Interface;

import gemini.FitGymGpt.DTO.Groq.BodyStatsRequest;
import gemini.FitGymGpt.DataBase.Model.WorkPlan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IWorkPlanController {

    @Operation(summary = "Cria um plano de treino para o usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano de treino criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<WorkPlan> createWorkPlan(@PathVariable Long userId, @RequestBody BodyStatsRequest request);

    @Operation(summary = "Obtém os planos de treino do usuário autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Planos de treino obtidos com sucesso"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    ResponseEntity<?> getMyWorkPlans(@AuthenticationPrincipal Object userObj);
}
