package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import gemini.FitGymGpt.strategy.register.ICeoRegisterValidations;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class CompanyNameLengthStrategy implements ICeoRegisterValidations {

    @Override
    @SneakyThrows
    public void registerCeoResponseValidations(CeoTrainerRegisterRequest request) {
        if (request.companyName().toString().length() < 3) {
            throw new IllegalArgumentException("O nome da empresa deve ter pelo menos 3 caracteres");
        }
    }
}
