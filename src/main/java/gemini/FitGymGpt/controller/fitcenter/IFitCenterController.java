package gemini.FitGymGpt.controller.fitcenter;

import gemini.FitGymGpt.dto.fitcenter.GymCenterRequest;
import gemini.FitGymGpt.dto.fitcenter.GymCenterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Validated
@Tag(name = "Gerenciamento de Academias", description = "Aqui estão os end points para gerenciamento de academias")
public interface IFitCenterController {

    @Operation(summary = "Endpoint para criar uma academia")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academia criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar academia"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "403", description = "Voce não tem essa permissão")
    })
    @PreAuthorize("hasAnyRole('ROOT')")
    @PostMapping("/fitCreate")
    ResponseEntity<GymCenterResponse> createFitCenter(GymCenterRequest request);

    @Operation(summary = "Endpoint para atualizar uma academia")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academia atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar academia"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "403", description = "Você não tem essa permissão")
    })
    @PreAuthorize("hasAnyRole('ROOT', 'CEO_TRAINER')")
    @PostMapping("/fitUpdate/{gymCenterId}")
    ResponseEntity<GymCenterResponse> updateGymCenter(@Parameter (description = "Id da academia") @PathVariable Long gymCenterId, @Valid @RequestBody GymCenterRequest request);

    @Operation(summary = "Endpoint para listar academias por nome")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academia atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar academia")
    })
    @GetMapping("/fitList")
    ResponseEntity<?> listGymCentersWithName(@Parameter(description = "Nome da academia") String name, @Parameter(description = "Número da página") int page, @Parameter(description = "Tamanho da página") int size);

    @Operation(summary = "EndPoint para listar todas as sessões das academias abertas")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academias listadas com sucesso"),
    })
    @GetMapping("/fitListSectionOpened")
    ResponseEntity<?> listSectionOpenedGymCenters(@Parameter(description = "Número da página") int page, @Parameter(description = "Tamanho da página") int size);

    @Operation(summary = "EndPoint para listar todas as academias em funcionamento")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academias listadas com sucesso"),
    })
    @GetMapping("/fitListGymOpened")
    ResponseEntity<?> listGymCenterOpened(@Parameter(description = "Horário de abertura da academia") LocalDate gymOpenedTime, @Parameter(description = "Número da página") int page, @Parameter(description = "Tamanho da página") int size);

    @Operation(summary = "EndPoint para listar todas as academias fora de funcionamento")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academias listadas com sucesso"),
    })
    @GetMapping("/fitListGymClosened")
    ResponseEntity<?> listGymCenterClosened(@Parameter(description = "Horário de abertura da academia") LocalDate gymClosedTime, @Parameter(description = "Número da página") int page, @Parameter(description = "Tamanho da página") int size);

    @Operation(summary = "EndPoint para listar todas as sessões das academias expiradas")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academias listadas com sucesso"),
    })
    @GetMapping("/fitListSectionExpired")
    ResponseEntity<?> listSectionExpiredGymCenters(@Parameter(description = "Número da página") int page, @Parameter(description = "Tamanho da página") int size);

    @Operation(summary = "EndPoint para listar todas as sessões das academias fechadas")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Academias listadas com sucesso"),
    })
    @GetMapping("/fitListSectionCanceled")
    ResponseEntity<?> listSectionCanceledGymCenters(@Parameter(description = "Número da página") int page, @Parameter(description = "Tamanho da página") int size);
}
