package gemini.FitGymGpt.dto.register.ceotrainer;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.Set;

public record CeoTrainerRegisterRequest(

             String username,
             String email,
             String password,
             Date birthDate,
             String gender,

            @NotBlank(message = "Nome da empresa é obrigatório")
             Set<GymCenterEntity> companyName
    )  {}

