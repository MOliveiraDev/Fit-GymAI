package gemini.FitGymGpt.controller.gemini;


import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IWorkPlanController {

    @Operation(summary = "Gera um plano de treino para o usuário")
    @ApiResponses ({
         @ApiResponse(responseCode = "200", description = "Plano de treino gerado com sucesso"),
         @ApiResponse(responseCode = "400", description = "Erro ao gerar plano de treino")
    })
    ResponseEntity<?> createWorkPlan(@PathVariable Long userId, @RequestBody BodyStatsRequest request);
}
