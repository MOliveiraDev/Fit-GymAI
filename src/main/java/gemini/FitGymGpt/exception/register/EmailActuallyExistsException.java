package gemini.FitGymGpt.exception.register;

public class EmailActuallyExistsException extends RuntimeException{

    public EmailActuallyExistsException(String email) {
        super("O email " + email + " já está cadastrado.");
    }

}
