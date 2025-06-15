package gemini.FitGymGpt.Controller.Auth.Interface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ILogOutController {

    @Operation(summary = "Logout")
    @ApiResponses
            ({
                @ApiResponse(responseCode = "200", description = "Logout feito com sucesso"),
                @ApiResponse(responseCode = "400", description = "Token invalido ou não fornecido")
            })
    ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String authorizationHeader);
}
