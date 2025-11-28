package gemini.FitGymGpt.controller.auth.Impl;

import gemini.FitGymGpt.controller.auth.IAuthController;
import gemini.FitGymGpt.dto.login.AuthRequest;
import gemini.FitGymGpt.dto.login.AuthResponse;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;
import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import gemini.FitGymGpt.dto.register.personal.PersonalRegisterRequest;
import gemini.FitGymGpt.service.auth.LoginService;
import gemini.FitGymGpt.service.auth.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthControllerImpl implements IAuthController {

    private final RegisterService registerService;
    private final LoginService loginService;

    @Override
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = registerService.register(registerRequest);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<RegisterResponse> registerCEO(@Valid @RequestBody CeoTrainerRegisterRequest registerRequest) {
        RegisterResponse response = registerService.registerCEO(registerRequest);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<RegisterResponse> registerPersonalTrainer(@Valid @RequestBody PersonalRegisterRequest registerRequest) {
        RegisterResponse response = registerService.registerPersonalTrainer(registerRequest);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = loginService.login(authRequest);
        return ResponseEntity.ok(response);
    }

}
