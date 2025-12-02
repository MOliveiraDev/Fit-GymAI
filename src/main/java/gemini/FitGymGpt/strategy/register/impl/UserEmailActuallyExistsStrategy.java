package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.database.repository.user.UserRepository;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmailActuallyExistsStrategy implements IRegisterValidations {

    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public void registerResponseValidations(@Valid RegisterRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new IllegalArgumentException("Este email " + request.email() + " ja foi cadastrado no sistema");
    }
}
