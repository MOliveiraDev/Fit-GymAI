package gemini.FitGymGpt.Config;

import gemini.FitGymGpt.DataBase.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInit implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (!userRepository.existsByUsername("admin")) {
            gemini.FitGymGpt.DataBase.Model.User admin = gemini.FitGymGpt.DataBase.Model.User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(gemini.FitGymGpt.DataBase.Model.Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Admin account \nusername: admin \npassword: admin123");
        } else {
            System.out.println("Admin account \nusername: admin \npassword: admin123");
        }
    }
}
