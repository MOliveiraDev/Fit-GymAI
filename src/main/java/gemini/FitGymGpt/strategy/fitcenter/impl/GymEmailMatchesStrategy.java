package gemini.FitGymGpt.strategy.fitcenter.impl;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class GymEmailMatchesStrategy implements IRegisterValidations {

    @Override
    @SneakyThrows
    public void registerResponseValidations(@Valid RegisterRequest request) {
        if (!request.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}
