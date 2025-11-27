package gemini.FitGymGpt.controller.auth.Impl;

import gemini.FitGymGpt.controller.auth.ILogOutController;
import gemini.FitGymGpt.service.jwt.TokenBlacklistService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class LogOutControllerImpl implements ILogOutController {

    private final TokenBlacklistService tokenBlacklistService;


    @Override
    public ResponseEntity<String> logout(
            @Parameter(description = "JWT token", required = true)
            @RequestHeader(value = "Authorization", required = false) String token
    ) {
        tokenBlacklistService.blacklistToken(token);
        return ResponseEntity.ok("Successfully logged out");
    }
}
