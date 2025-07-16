package gemini.FitGymGpt.controller.auth.Impl;

import gemini.FitGymGpt.controller.auth.ILogOutController;
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
public class LogOutControllerImpl implements ILogOutController {

}
