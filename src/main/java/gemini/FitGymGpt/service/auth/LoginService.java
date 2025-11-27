package gemini.FitGymGpt.service.auth;


import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.enums.UserStatus;
import gemini.FitGymGpt.database.repository.user.UserRepository;
import gemini.FitGymGpt.dto.login.AuthRequest;
import gemini.FitGymGpt.dto.login.AuthResponse;
import gemini.FitGymGpt.exception.login.EmailNotFoundException;
import gemini.FitGymGpt.exception.login.PasswordIsIncorrectException;
import gemini.FitGymGpt.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Transactional
    public AuthResponse login(AuthRequest request) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );


            UserEntity user = (UserEntity) authentication.getPrincipal();
            user.setUserStatus(UserStatus.ONLINE);
            userRepository.save(user);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            return new AuthResponse(jwtToken);
        } catch (BadCredentialsException e) {
            throw new PasswordIsIncorrectException("Senha incorreta");
        }   catch (Exception e ) {
            throw new EmailNotFoundException("Email não encontrado");
        }
    }

}