package gemini.FitGymGpt.exception.login;

public class PasswordIsIncorrectException extends RuntimeException {

    public PasswordIsIncorrectException(String username) {
        super("Senha incorreta para o usuário " + username);
    }
}
