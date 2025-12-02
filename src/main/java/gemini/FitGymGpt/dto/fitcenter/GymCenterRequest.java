package gemini.FitGymGpt.dto.fitcenter;

import java.time.LocalTime;

public record GymCenterRequest(

        String gymCenterName,
        String gymDescription,
        String gymPhone,
        String gymEmail,
        String taxId,
        String gymImageUrl,
        String gymCenterAddress,
        String gymWebsite,
        LocalTime openingTime,
        LocalTime closingTime

        ) {
}
