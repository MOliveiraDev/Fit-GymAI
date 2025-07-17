package gemini.FitGymGpt.exceptions.auth;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String username) {
        super("Usuário " + username + " não encontrado");
    }
}
