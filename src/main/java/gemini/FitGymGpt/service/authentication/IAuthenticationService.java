package gemini.FitGymGpt.service.authentication;

import gemini.FitGymGpt.dto.auth.AuthRequest;
import gemini.FitGymGpt.dto.auth.AuthResponse;

public interface IAuthenticationService {

    AuthResponse login(AuthRequest request) throws Exception;
}
