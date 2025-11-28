package gemini.FitGymGpt.strategy.fitcenter;

import gemini.FitGymGpt.dto.fitcenter.CreateGymCenterRequest;
import jakarta.validation.Valid;

public interface IGymCenterValidations {

    void gymCenterValidations(@Valid CreateGymCenterRequest request);
}
