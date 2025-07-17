package gemini.FitGymGpt.service.register;

import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;

public interface IRegisterService {

    RegisterResponse register(RegisterRequest registerRequest) throws Exception;
}
