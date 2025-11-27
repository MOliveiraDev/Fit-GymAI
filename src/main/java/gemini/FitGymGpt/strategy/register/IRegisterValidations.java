package gemini.FitGymGpt.strategy.register;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import jakarta.validation.Valid;

public interface IRegisterValidations {

    void registerResponseValidations(@Valid RegisterRequest request);
}
