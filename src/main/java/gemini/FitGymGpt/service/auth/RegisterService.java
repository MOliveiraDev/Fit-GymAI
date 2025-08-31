package gemini.FitGymGpt.service.auth;

import gemini.FitGymGpt.database.repository.UserRepository;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;
import gemini.FitGymGpt.exceptions.register.EmailActuallyExistsException;
import gemini.FitGymGpt.strategy.IRegisterValidations;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<IRegisterValidations> registerValidationsList;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        registerValidationsList.forEach(strategy -> strategy.registerResponseValidations(request));

        var user = userRepository.save(
                gemini.FitGymGpt.database.model.User.builder()
                        .username(request.getUsername())

                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(gemini.FitGymGpt.database.model.Role.USER)
                        .build()
        );
        return RegisterResponse.builder()
                .message("Usuário registrado com sucesso")
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public RegisterResponse registerAdmin(RegisterRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailActuallyExistsException("Email já cadastrado");
        }

        var user = userRepository.save(
                gemini.FitGymGpt.database.model.User.builder()
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(gemini.FitGymGpt.database.model.Role.ADMIN)
                        .build()
        );
        return RegisterResponse.builder()
                .message("Administrador registrado com sucesso")
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
