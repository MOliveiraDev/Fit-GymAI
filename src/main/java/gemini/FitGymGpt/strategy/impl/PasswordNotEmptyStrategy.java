package gemini.FitGymGpt.strategy.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.IRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordNotEmptyStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(RegisterRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }
        if (request.getConfirmPassword() == null || request.getConfirmPassword().isEmpty()) {
            throw new IllegalArgumentException("A confirmação de senha não pode ser vazia");
        }
    }
}
