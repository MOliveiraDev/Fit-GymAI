package gemini.FitGymGpt.controller.auth.Impl;

import gemini.FitGymGpt.controller.auth.IAuthController;
import gemini.FitGymGpt.database.repository.UserRepository;
import gemini.FitGymGpt.dto.auth.AuthRequest;
import gemini.FitGymGpt.dto.auth.AuthResponse;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;
import gemini.FitGymGpt.service.authentication.impl.AuthenticationService;
import gemini.FitGymGpt.service.jwt.JwtService;
import gemini.FitGymGpt.service.register.impl.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthControllerImpl implements IAuthController {

    private final RegisterService registerService;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = registerService.register(registerRequest);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = authenticationService.login(authRequest);
        return ResponseEntity.ok(response);
    }
}
