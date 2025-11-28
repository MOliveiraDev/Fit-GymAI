package gemini.FitGymGpt.strategy.register;

import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import jakarta.validation.Valid;

public interface ICeoRegisterValidations {

    void registerCeoResponseValidations(@Valid CeoTrainerRegisterRequest request);

}
