package gemini.FitGymGpt.controller.auth;

import gemini.FitGymGpt.dto.login.AuthRequest;
import gemini.FitGymGpt.dto.login.AuthResponse;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;
import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import gemini.FitGymGpt.dto.register.personal.PersonalRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "Autenticação", description = "APIs para autenticação e registro de usuários")
public interface IAuthController {

    @Operation(summary = "Registrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Usuário já existe"),
    })
    @PostMapping(value = "/register")
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest);

    @Operation(summary = "Registrar um novo CEO Trainer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CEO Trainer registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "CEO já existe"),
    })
    @PostMapping(value = "/register/admin")
    @PreAuthorize("hasRole('ROOT')")
    ResponseEntity<RegisterResponse> registerCEO(@Valid @RequestBody CeoTrainerRegisterRequest registerRequest);

    @Operation(summary = "Registrar um novo Personal Trainer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personal Trainer registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Personal Trainer já existe"),
    })
    @PostMapping(value = "/register/admin")
    @PreAuthorize("hasAnyRole('ROOT', 'CEO_TRAINER')")
    ResponseEntity<RegisterResponse> registerPersonalTrainer(@Valid @RequestBody PersonalRegisterRequest registerRequest);

    @Operation(summary = "Autenticar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping(value = "/login")
    ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest);
}