package gemini.FitGymGpt.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("FitGymGPT API")
                        .version("1.0.0")
                        .description("API for FitGymGPT application")
                        .contact(new Contact()
                                .name("OliverDev")
                                .url("https://github.com/MOliveiraDev/Fit-GymAI")
                                .email("email@email.com")));
    }
}
