package gemini.FitGymGpt.Exceptions.Auth;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super("Usuário " + username + " não encontrado");
    }
}
