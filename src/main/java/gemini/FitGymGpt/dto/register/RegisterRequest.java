package gemini.FitGymGpt.dto.register;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Nome do usuário não pode ser vazio")
    private String username;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    private String password;

    @Transient
    @NotBlank(message = "Confirmação de senha não pode ser vazia")
    private String confirmPassword;

}
