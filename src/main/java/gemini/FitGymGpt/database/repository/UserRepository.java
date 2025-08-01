package gemini.FitGymGpt.database.repository;

import gemini.FitGymGpt.database.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
