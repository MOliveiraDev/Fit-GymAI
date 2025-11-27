package gemini.FitGymGpt.exception;

import gemini.FitGymGpt.exception.login.EmailNotFoundException;
import gemini.FitGymGpt.exception.login.PasswordIsIncorrectException;
import gemini.FitGymGpt.exception.gemini.GeminiApiException;
import gemini.FitGymGpt.exception.register.EmailActuallyExistsException;
import gemini.FitGymGpt.exception.register.UsernameActuallyExistsException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Hidden
public class GlobalExcepitonHandler {

    @ExceptionHandler
    public ResponseEntity<ApiException> handleEmailNotFoundException(EmailNotFoundException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Usuário não encontrado",
                404,
                LocalDateTime.now()
        );
        return ResponseEntity.status(404).body(apiException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleGeneralException(Exception ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Erro interno do servidor",
                500,
                LocalDateTime.now()
        );
        return ResponseEntity.status(500).body(apiException);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Argumento inválido fornecido",
                400,
                LocalDateTime.now()
        );
        return ResponseEntity.status(400).body(apiException);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiException> handleGeminiException(NullPointerException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Erro de ponteiro nulo",
                500,
                LocalDateTime.now()
        );
        return ResponseEntity.status(500).body(apiException);
    }

    @ExceptionHandler(PasswordIsIncorrectException.class)
    public ResponseEntity<ApiException> handlePasswordIsIncorrectException(PasswordIsIncorrectException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Senha incorreta",
                401,
                LocalDateTime.now()
        );
        return ResponseEntity.status(401).body(apiException);
    }

    @ExceptionHandler(EmailActuallyExistsException.class)
    public ResponseEntity<ApiException> handleEmailActuallyExistsException(EmailActuallyExistsException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Email já existe",
                409,
                LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(apiException);
    }

    @ExceptionHandler(UsernameActuallyExistsException.class)
    public ResponseEntity<ApiException> handleUsernameActuallyExistsException(UsernameActuallyExistsException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Nome de usuário já existe",
                409,
                LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(apiException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError() != null ?
                ex.getBindingResult().getFieldError().getDefaultMessage() : "Dados inválidos fornecidos";
        ApiException apiException = new ApiException(
                errorMessage,
                "Erro de validação",
                400,
                LocalDateTime.now()
        );
        return ResponseEntity.status(400).body(apiException);
    }

    @ExceptionHandler(GeminiApiException.class)
    public ResponseEntity<ApiException> handleGeminiApiException(GeminiApiException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                "Erro ao se comunicar com a Gemini API",
                502,
                LocalDateTime.now()
        );
        return ResponseEntity.status(502).body(apiException);
    }
}
