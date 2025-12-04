package gemini.FitGymGpt.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Logout")
public interface ILogOutController {

    @Operation(summary = "Log out userEntity", description = "Logs out the userEntity by invalidating the JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged out"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or expired token")
    })
    @PostMapping("/logout")
    ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String token);
}
