package gemini.FitGymGpt.database.repository.fitcenter;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.enums.GymStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymCenterRepository extends JpaRepository<GymCenterEntity, Long> {

    List<GymCenterEntity> findByGymStatus(GymStatus status);

    List<GymCenterEntity> findByGymCenterNameContainingIgnoreCase(String gymCenterName);

    List<GymCenterEntity> findByGymCenterId(Long gymCenterId);

    boolean existsByTaxId(String taxId);
    boolean existsByGymCenterName(String gymCenterName);
    boolean existsByGymEmail(String gymEmail);

    Optional<GymCenterEntity> findByTaxId(String taxId);
    Optional<GymCenterEntity> findByGymEmail(String gymEmail);

    Long gymCenterId(Long gymCenterId);
}
