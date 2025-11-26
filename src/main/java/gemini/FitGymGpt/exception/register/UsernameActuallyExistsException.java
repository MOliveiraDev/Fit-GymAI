package gemini.FitGymGpt.exception.register;

public class UsernameActuallyExistsException extends RuntimeException {

    public UsernameActuallyExistsException(String username) {
        super("O nome de usuário " + username + " já está cadastrado.");
    }

    public UsernameActuallyExistsException(String username, Throwable cause) {
        super("O nome de usuário " + username + " já está cadastrado.", cause);
    }
}
