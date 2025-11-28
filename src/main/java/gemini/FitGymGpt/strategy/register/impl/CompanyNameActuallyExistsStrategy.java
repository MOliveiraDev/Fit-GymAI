package gemini.FitGymGpt.strategy.register.impl;

import gemini.FitGymGpt.database.repository.fitcenter.GymCenterEntityRepository;
import gemini.FitGymGpt.strategy.register.ICeoRegisterValidations;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyNameActuallyExistsStrategy implements ICeoRegisterValidations {

    private final GymCenterEntityRepository gymCenterEntityRepository;

    @Override
    @SneakyThrows
    public void registerCeoResponseValidations(gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest request) {
        if (gymCenterEntityRepository.findByGymCenterName(request.companyName())) {
            throw new IllegalArgumentException("Essa empresa já está cadastrada");
        }

    }
}
