package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import gemini.FitGymGpt.strategy.register.ICeoRegisterValidations;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class CompanyNameEmptyStrategy implements ICeoRegisterValidations {

    @Override
    @SneakyThrows
    public void registerCeoResponseValidations(@Valid CeoTrainerRegisterRequest request) {
        if (request.companyName().isEmpty()) {
            throw new IllegalArgumentException("Preencha o nome da empresa");
        }
    }
}
