package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class UsernameNotEmptyStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(@Valid RegisterRequest request) {
        if (request.username() == null || request.username().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode estar em branco");
        }
    }
}
