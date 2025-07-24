package gemini.FitGymGpt.config;

import gemini.FitGymGpt.database.model.Role;
import gemini.FitGymGpt.database.model.User;
import gemini.FitGymGpt.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@fitgymgpt.com";
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User adminUser = User.builder()
                    .username("Administrador")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin123"))
                    .confirmPassword("admin123")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(adminUser);

            System.out.println("ADMIN USER SEEDED: \n " +
                    "Email: " + adminEmail + "\n " +
                    "Password: admin123");
        }

        else {
            System.out.println("ADMIN USER SEEDED: \n " +
                    "Email: " + adminEmail + "\n " +
                    "Password: admin123");
        }
    }
}