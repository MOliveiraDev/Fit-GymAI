package gemini.FitGymGpt.strategy.fitcenter.impl;

import gemini.FitGymGpt.database.repository.fitcenter.GymCenterRepository;
import gemini.FitGymGpt.dto.fitcenter.GymCenterRequest;
import gemini.FitGymGpt.strategy.fitcenter.IGymCenterValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GymEmailActuallyExistsStrategy implements IGymCenterValidations {

    private final GymCenterRepository gymCenterRepository;

    @Override
    @SneakyThrows
    public void gymCenterValidations(@Valid GymCenterRequest request) {
        if (gymCenterRepository.existsByGymEmail(request.gymEmail())) {
            throw new IllegalArgumentException("Este email " + request.gymEmail() + " ja está cadastrado no sistema");
        }
    }


}
