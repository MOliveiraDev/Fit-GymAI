package gemini.FitGymGpt.exception.login;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String username) {
        super("Usuário " + username + " não encontrado");
    }
}
