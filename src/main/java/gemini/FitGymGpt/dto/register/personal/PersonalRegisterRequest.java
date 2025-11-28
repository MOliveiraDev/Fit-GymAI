package gemini.FitGymGpt.dto.register.personal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PersonalRegisterRequest(
        String username,
        String email,
        String password,
        Date birthDate,
        String gender,

        @NotBlank(message = "CREF é obrigatório")
        String crefNumber,

        @NotBlank(message = "Especialização é obrigatória")
        String specialization,

        @NotNull(message = "Anos de experiência é obrigatório")
        Integer yearsExperience
) {
}
