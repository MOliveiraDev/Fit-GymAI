package gemini.FitGymGpt.Exceptions;

import gemini.FitGymGpt.Exceptions.Auth.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExcepitonHandler {

    @ExceptionHandler
    public ResponseEntity<ApiException> handleUserNotFoundException(UserNotFoundException ex) {
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
}
