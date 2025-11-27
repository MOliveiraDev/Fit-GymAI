package gemini.FitGymGpt.exception.register;

public class UsernameActuallyExistsException extends RuntimeException {

    public UsernameActuallyExistsException(String username) {
        super("O nome de usuário " + username + " já está cadastrado.");
    }
}
