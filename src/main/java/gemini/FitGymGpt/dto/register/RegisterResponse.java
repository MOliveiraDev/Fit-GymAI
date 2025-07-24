package gemini.FitGymGpt.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private String message;
    private String username;
    private String email;
}
