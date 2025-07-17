package gemini.FitGymGpt.service.authentication.impl;

import gemini.FitGymGpt.dto.auth.AuthRequest;
import gemini.FitGymGpt.dto.auth.AuthResponse;
import gemini.FitGymGpt.exceptions.auth.EmailNotFoundException;
import gemini.FitGymGpt.exceptions.auth.PasswordIsIncorrectException;
import gemini.FitGymGpt.service.authentication.IAuthenticationService;
import gemini.FitGymGpt.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (BadCredentialsException e) {
            throw new EmailNotFoundException("Email ou senha inválidos");
        } catch (Exception e) {
            throw new PasswordIsIncorrectException("Senha incorreta ou usuário não encontrado");
        }
    }
}