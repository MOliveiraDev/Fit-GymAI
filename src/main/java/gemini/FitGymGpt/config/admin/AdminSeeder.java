package gemini.FitGymGpt.config.admin;

import gemini.FitGymGpt.enums.UserRoles;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.database.repository.user.UserRepository;
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
        String adminEmail = "rootmail@fitgymgpt.com";
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("USER ROOT");
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode("Root@123"));
            adminUser.setUserRoles(UserRoles.ROOT);

            userRepository.save(adminUser);
            System.out.println("Admin user created with email: " + adminEmail);
        } else {
            System.out.println("Admin user already exists with email: " + adminEmail);
        }
    }
}