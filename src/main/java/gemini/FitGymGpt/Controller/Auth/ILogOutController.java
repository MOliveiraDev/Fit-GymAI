package gemini.FitGymGpt.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ILogOutController {

    @Operation(summary = "Log out user", description = "Logs out the user by invalidating the JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged out"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or expired token")
    })
    @PostMapping("/logout")
    ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String token);
}
