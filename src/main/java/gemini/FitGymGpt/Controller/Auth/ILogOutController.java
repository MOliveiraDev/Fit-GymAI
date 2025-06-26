package gemini.FitGymGpt.Controller.Auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ILogOutController {

    @Operation(summary = "Logout de usuários")
    @ApiResponses ({
        @ApiResponse(responseCode = "200", description = "Logout bem-sucedido"),
        @ApiResponse(responseCode = "400", description = "Erro ao realizar logout")
    })
    ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String authorizationHeader);
}
