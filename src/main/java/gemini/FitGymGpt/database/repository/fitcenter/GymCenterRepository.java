package gemini.FitGymGpt.database.repository.fitcenter;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.enums.GymStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymCenterRepository extends JpaRepository<GymCenterEntity, Long> {

    Page<GymCenterEntity> findByGymStatus(GymStatus status, Pageable pageable);

    Page<GymCenterEntity> findByGymCenterNameContainingIgnoreCase(String gymCenterName, Pageable pageable);

    boolean existsByTaxId(String taxId);
    boolean existsByGymCenterName(String gymCenterName);
    boolean existsByGymEmail(String gymEmail);

}
