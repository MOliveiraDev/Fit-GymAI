package gemini.FitGymGpt.strategy;

import gemini.FitGymGpt.dto.register.RegisterRequest;

public interface IRegisterValidations {

    void registerResponseValidations(RegisterRequest request);
}
