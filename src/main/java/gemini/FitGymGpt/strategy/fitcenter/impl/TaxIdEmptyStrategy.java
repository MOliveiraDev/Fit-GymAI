package gemini.FitGymGpt.strategy.fitcenter.impl;

import gemini.FitGymGpt.dto.fitcenter.CreateGymCenterRequest;
import gemini.FitGymGpt.strategy.fitcenter.IGymCenterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class TaxIdEmptyStrategy implements IGymCenterValidations {

    @Override
    @SneakyThrows
    public void gymCenterValidations(@Valid CreateGymCenterRequest request) {
        if (request.taxId().isEmpty()) {
            throw new IllegalArgumentException("Preencha o CNPJ");
        }
    }
}
