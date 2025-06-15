package gemini.FitGymGpt.Controller.Auth.Interface;

import gemini.FitGymGpt.DTO.Auth.AuthRequest;
import gemini.FitGymGpt.DTO.Auth.AuthResponse;
import gemini.FitGymGpt.DataBase.Model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    @Operation(summary = "Cadastrar")
    @ApiResponses(
            {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
            }
            )
    ResponseEntity<User> register(@RequestBody User user);

    @Operation(summary = "Login")
    @ApiResponses(
            {
            @ApiResponse(responseCode = "200", description = "Login feito com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            }
            )
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);

}
