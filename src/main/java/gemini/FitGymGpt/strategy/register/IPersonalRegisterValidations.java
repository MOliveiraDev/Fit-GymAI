package gemini.FitGymGpt.strategy.register;

import gemini.FitGymGpt.dto.register.personal.PersonalRegisterRequest;
import jakarta.validation.Valid;

public interface IPersonalRegisterValidations {

    void personalRegisterValidations(@Valid PersonalRegisterRequest request);
}
