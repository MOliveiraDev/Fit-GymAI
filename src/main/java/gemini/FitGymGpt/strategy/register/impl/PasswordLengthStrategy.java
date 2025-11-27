package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordLengthStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(@Valid RegisterRequest request) {
        if (request.password().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres");
        }
    }
}
