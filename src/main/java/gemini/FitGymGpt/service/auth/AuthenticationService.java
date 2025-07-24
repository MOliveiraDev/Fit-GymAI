package gemini.FitGymGpt.service.auth;

import gemini.FitGymGpt.dto.auth.AuthRequest;
import gemini.FitGymGpt.dto.auth.AuthResponse;
import gemini.FitGymGpt.exceptions.auth.EmailNotFoundException;
import gemini.FitGymGpt.exceptions.auth.PasswordIsIncorrectException;
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
public class AuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            return AuthResponse.builder()
                    .email(userDetails.getUsername())
                    .token(jwtToken)
                    .build();
        } catch (BadCredentialsException e) {
            throw new PasswordIsIncorrectException("Senha incorreta");
    }   catch (Exception e ) {
            throw new EmailNotFoundException("Email não encontrado");
        }
    }
}