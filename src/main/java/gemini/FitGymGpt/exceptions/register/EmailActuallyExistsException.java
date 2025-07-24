package gemini.FitGymGpt.exceptions.register;

public class EmailActuallyExistsException extends RuntimeException{

    public EmailActuallyExistsException(String email) {
        super("O email " + email + " já está cadastrado.");
    }

    public EmailActuallyExistsException(String email, Throwable cause) {
        super("O email " + email + " já está cadastrado.", cause);
    }
}
