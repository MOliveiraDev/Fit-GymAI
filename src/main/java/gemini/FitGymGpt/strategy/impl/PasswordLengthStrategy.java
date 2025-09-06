package gemini.FitGymGpt.strategy.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.IRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordLengthStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {
        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres");
        }
    }
}
