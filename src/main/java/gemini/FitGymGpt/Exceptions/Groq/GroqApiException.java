package gemini.FitGymGpt.Exceptions.Groq;

public class GroqApiException extends RuntimeException{

    public GroqApiException(String message) {
        super(message);
    }

    public GroqApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroqApiException(Throwable cause) {
        super(cause);
    }
}
