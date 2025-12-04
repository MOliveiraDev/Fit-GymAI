package gemini.FitGymGpt.dto.register.ceotrainer;



import java.util.Date;
import java.util.Set;

public record CeoTrainerRegisterRequest(

             String username,
             String email,
             String password,
             Date birthDate,
             String gender,

             Integer yearsExperience
    )  {}

