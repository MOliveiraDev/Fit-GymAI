package gemini.FitGymGpt.database.repository.personal;

import gemini.FitGymGpt.database.domain.personal.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<PersonalEntity, Long> {
}