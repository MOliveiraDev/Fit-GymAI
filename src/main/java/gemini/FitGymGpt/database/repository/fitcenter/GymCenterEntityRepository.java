package gemini.FitGymGpt.database.repository.fitcenter;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymCenterEntityRepository extends JpaRepository<GymCenterEntity, Long> {


    @Query("SELECT g FROM GymCenterEntity g WHERE g.gymStatus = 'OPEN'")
    List<GymCenterEntity> findOpenGymCenters();

    @Query("SELECT g FROM GymCenterEntity g WHERE g.gymStatus = 'CLOSE'")
    List<GymCenterEntity> findCloseGymCenters();

    GymCenterEntity findByGymCenterId(Long gymCenterId);

    boolean findByGymCenterName(String gymCenterName);
}