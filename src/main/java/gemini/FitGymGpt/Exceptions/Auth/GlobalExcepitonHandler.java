package gemini.FitGymGpt.Exceptions.Auth;

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
}
