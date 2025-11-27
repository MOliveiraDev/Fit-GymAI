package gemini.FitGymGpt.dto.fitcenter.creategymcenter;

import java.time.LocalTime;

public record CreateGymCenterRequest(

        String gymCenterName,
        String gymDescription,
        String gymPhone,
        String gymEmail,
        String gymCenterAddress,
        String gymWebsite,
        LocalTime openingTime,
        LocalTime closingTime

        ) {
}
