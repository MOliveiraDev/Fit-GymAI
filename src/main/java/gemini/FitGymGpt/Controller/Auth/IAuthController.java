package gemini.FitGymGpt.Controller.Auth;

import gemini.FitGymGpt.DTO.Auth.AuthRequest;
import gemini.FitGymGpt.DTO.Auth.AuthResponse;
import gemini.FitGymGpt.DataBase.Model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface IAuthController {

    @Operation(summary = "Registro para novos usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuario já existe ou dados inválidos"),
    })
    ResponseEntity<User> register(@Valid @RequestBody User user);

    @Operation(summary = "Registro para novos usuários com papel de administrador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário administrador registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já existe ou dados inválidos"),
    })
    ResponseEntity<User> registerAdmin(@Valid @RequestBody User user);

    @Operation(summary = "Login de usuários existentes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request);
}
