package gemini.FitGymGpt.strategy.fitcenter;

import gemini.FitGymGpt.dto.fitcenter.GymCenterRequest;
import jakarta.validation.Valid;

public interface IGymCenterValidations {

    void gymCenterValidations(@Valid GymCenterRequest request);
}
