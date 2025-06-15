package gemini.FitGymGpt.Controller.Auth;

import gemini.FitGymGpt.Controller.Auth.Interface.ILogOutController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class LogOutController implements ILogOutController {

    private static final Set<String> blacklistedTokens = new HashSet<>();

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            blacklistedTokens.add(token);
            return ResponseEntity.ok("Logout feito com sucesso");
        }
        return ResponseEntity.badRequest().body("Token invalido ou não fornecido");

    }

    public static boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

}
