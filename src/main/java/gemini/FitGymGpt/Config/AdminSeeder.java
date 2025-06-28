package gemini.FitGymGpt.Config;

import gemini.FitGymGpt.DataBase.Model.Role;
import gemini.FitGymGpt.DataBase.Model.User;
import gemini.FitGymGpt.DataBase.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("ADM ACCOUNT \n Username: admin \n Password: admin123");
        }
        else {
            System.out.println("ADM ACCOUNT \n Username: admin \n Password: admin123");
        }
    }
}
