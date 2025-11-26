package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class EmailNotEmptyStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(@Valid RegisterRequest request) {
        if (request.email() == null || request.email().isEmpty()) {
            throw new IllegalArgumentException("O email não pode estar em branco");
        }
    }
}
