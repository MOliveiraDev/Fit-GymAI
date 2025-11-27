package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordNotEmptyStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(@Valid RegisterRequest request) {
        if (request.password() == null || request.password().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }
    }
}
