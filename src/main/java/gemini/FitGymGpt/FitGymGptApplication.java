package gemini.FitGymGpt;

import gemini.FitGymGpt.Config.AdminInit;
import gemini.FitGymGpt.DataBase.Repository.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FitGymGptApplication extends AdminInit {

	public FitGymGptApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super(userRepository, passwordEncoder);
	}

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(FitGymGptApplication.class, args);
	}

}
