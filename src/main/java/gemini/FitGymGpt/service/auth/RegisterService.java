package gemini.FitGymGpt.service.auth;

import gemini.FitGymGpt.database.domain.user.Role;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.database.repository.user.UserRepository;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<IRegisterValidations> registerValidationsList;

    @Transactional
    public RegisterResponse register(@Valid RegisterRequest request) {

        validateRequest(request);
        UserEntity user = buildBaseUser(request, Role.USER);
        userRepository.save(user);

        return new RegisterResponse("New User Registred");
    }

    @Transactional
    public RegisterResponse registerCEO(RegisterRequest request) {

        validateRequest(request);
        UserEntity user = buildBaseUser(request, Role.CEO_TRAINER);
        userRepository.save(user);

        return new RegisterResponse("New CEO Trainer Registred");

    }

    @Transactional
    public RegisterResponse registerPersonalTrainer(RegisterRequest request) {

        validateRequest(request);
        UserEntity user = buildBaseUser(request, Role.PERSONAL_TRAINER);
        userRepository.save(user);

        return new RegisterResponse("New Personal Trainer Registred");
    }

    private UserEntity buildBaseUser(RegisterRequest request, Role role){

        UserEntity user = new UserEntity();
        user.setUsername(request.username());
        user.setBirthdayDate(request.birthDate());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setGender(request.gender());
        user.setBirthdayDate(request.birthDate());
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setAge(calculateAge((Date) request.birthDate()));
        user.setRole(role);

        return user;

    }

    private void validateRequest(RegisterRequest request) {
        registerValidationsList.forEach(strategy -> strategy.registerResponseValidations(request));
    }

    private int calculateAge(Date birthDate) {
        if (birthDate == null) return 0;

        java.time.LocalDate birthLocalDate = new java.sql.Date(birthDate.getTime()).toLocalDate();
        java.time.LocalDate today = java.time.LocalDate.now();

        return java.time.Period.between(birthLocalDate, today).getYears();
    }

}
