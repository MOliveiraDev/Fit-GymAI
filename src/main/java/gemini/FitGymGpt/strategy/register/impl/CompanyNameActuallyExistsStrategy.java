package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.database.repository.fitcenter.GymCenterRepository;
import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import gemini.FitGymGpt.strategy.register.ICeoRegisterValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyNameActuallyExistsStrategy implements ICeoRegisterValidations {

    private final GymCenterRepository gymCenterRepository;

    @Override
    @SneakyThrows
    public void registerCeoResponseValidations(@Valid CeoTrainerRegisterRequest request) {
        if (gymCenterRepository.existsByGymCenterName(request.companyName().toString())) {
            throw new IllegalArgumentException("Essa empresa já está cadastrada");
        }

    }
}
