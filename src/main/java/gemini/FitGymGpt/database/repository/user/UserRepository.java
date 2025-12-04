package gemini.FitGymGpt.database.repository.user;

import gemini.FitGymGpt.database.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean findByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Object> findByUserId(UUID userId);
}
