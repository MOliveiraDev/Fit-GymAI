package gemini.FitGymGpt.dto.register;

import ch.qos.logback.core.boolex.Matcher;

import java.util.Date;

public record RegisterRequest(
        String username,
        String email,
        String password,
        Date birthDate,
        String gender) {
}