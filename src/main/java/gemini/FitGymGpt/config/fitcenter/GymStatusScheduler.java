package gemini.FitGymGpt.config.fitcenter;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.enums.GymStatus;
import gemini.FitGymGpt.database.repository.fitcenter.GymCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GymStatusScheduler {

    private final GymCenterRepository gymCenterRepository;

    @Scheduled(fixedRate = 60000)
    public void updateGymStatus() {

        LocalTime now = LocalTime.now();

        List<GymCenterEntity> gyms = gymCenterRepository.findAll();

        for (GymCenterEntity gym : gyms) {
            GymStatus newStatus = isGymOpen(now, gym.getOpeningTime(), gym.getClosingTime())
                    ? GymStatus.OPEN
                    : GymStatus.CLOSED;

            if (!newStatus.equals(gym.getGymStatus())) {
                gym.setGymStatus(newStatus);
                gymCenterRepository.save(gym);
            }
        }
    }

    private boolean isGymOpen(LocalTime now, LocalTime opening, LocalTime closing) {
        return now.isAfter(opening) && now.isBefore(closing);
    }
}
