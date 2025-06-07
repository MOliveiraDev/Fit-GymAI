package gemini.FitGymGpt.Controller.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/auth/logout")
@RequiredArgsConstructor
@RestController
public class LogOutController {

    private static final Set<String> blacklistedTokens = new HashSet<>();

    @PostMapping()
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authReader) {
        if (authReader != null && authReader.startsWith("Bearer ")) {
            String token = authReader.substring(7);
            blacklistedTokens.add(token);
            return ResponseEntity.ok("Desconectado com sucesso");
        }
        return ResponseEntity.badRequest().body("Token inválido ou não fornecido");
    }

    public static boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
