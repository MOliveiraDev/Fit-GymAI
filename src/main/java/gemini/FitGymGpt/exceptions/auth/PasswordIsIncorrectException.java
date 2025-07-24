package gemini.FitGymGpt.exceptions.auth;

public class PasswordIsIncorrectException extends RuntimeException {

    public PasswordIsIncorrectException(String username) {
        super("Senha incorreta para o usuário " + username);
    }
}
