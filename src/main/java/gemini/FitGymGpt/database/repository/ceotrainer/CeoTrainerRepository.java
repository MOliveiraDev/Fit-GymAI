package gemini.FitGymGpt.database.repository.ceotrainer;

import gemini.FitGymGpt.database.domain.ceotrainer.CeoTrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CeoTrainerRepository extends JpaRepository<CeoTrainerEntity, Long> {
}