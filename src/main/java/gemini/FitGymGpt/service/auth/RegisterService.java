package gemini.FitGymGpt.service.auth;

import gemini.FitGymGpt.database.domain.ceotrainer.CeoTrainerEntity;
import gemini.FitGymGpt.database.domain.personal.PersonalEntity;
import gemini.FitGymGpt.database.repository.ceotrainer.CeoTrainerRepository;
import gemini.FitGymGpt.database.repository.personal.PersonalRepository;
import gemini.FitGymGpt.dto.register.ceotrainer.CeoTrainerRegisterRequest;
import gemini.FitGymGpt.dto.register.personal.PersonalRegisterRequest;
import gemini.FitGymGpt.enums.UserRoles;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.enums.UserStatus;
import gemini.FitGymGpt.database.repository.user.UserRepository;
import gemini.FitGymGpt.dto.register.RegisterRequest;
import gemini.FitGymGpt.dto.register.RegisterResponse;
import gemini.FitGymGpt.strategy.register.ICeoRegisterValidations;
import gemini.FitGymGpt.strategy.register.IPersonalRegisterValidations;
import gemini.FitGymGpt.strategy.register.IRegisterValidations;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<IRegisterValidations> registerValidationsList;
    private final List<ICeoRegisterValidations> registerCeoValidationsList;
    private final List<IPersonalRegisterValidations> personalRegisterValidations;
    private final PersonalRepository personalRepository;
    private final CeoTrainerRepository ceoTrainerRepository;

    @Transactional
    public RegisterResponse register(@Valid RegisterRequest request) {
        validateRequest(request);
        UserEntity user = buildBaseUser(request, UserRoles.USER);
        userRepository.save(user);
        return new RegisterResponse("New User Registered");
    }

    @Transactional
    public RegisterResponse registerCEO(@Valid CeoTrainerRegisterRequest request) {
        validateCeoRequest(request);
        UserEntity user = buildBaseUser(request);
        userRepository.save(user);


        CeoTrainerEntity ceoProfile = new CeoTrainerEntity();
        ceoProfile.setUser(user);
        ceoProfile.setGymCenters(request.companyName());
        ceoTrainerRepository.save(ceoProfile);

        return new RegisterResponse("New CEO Trainer Registered");
    }

    @Transactional
    public RegisterResponse registerPersonalTrainer(@Valid PersonalRegisterRequest request) {
        validatePersonalRequest(request);
        UserEntity user = buildBaseUser(request);
        userRepository.save(user);

        PersonalEntity personalProfile = new PersonalEntity();
        personalProfile.setUser(user);
        personalProfile.setCrefNumber(request.crefNumber());
        personalProfile.setSpecialization(request.specialization());
        personalProfile.setYearsExperience(request.yearsExperience());
        personalRepository.save(personalProfile);

        return new RegisterResponse("New Personal Trainer Registered");
    }

    private UserEntity createBaseUser(String username, Date birthDate, String email, String rawPassword, String gender, UserRoles role) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setAge(calculateAge(birthDate));
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setGender(gender);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUserRoles(role);
        user.setUserStatus(UserStatus.OFFLINE);
        return user;
    }

    private UserEntity buildBaseUser(@Valid RegisterRequest request, UserRoles userRoles) {
        return createBaseUser(request.username(), (Date) request.birthDate(), request.email(), request.password(), request.gender(), userRoles);
    }

    private UserEntity buildBaseUser(@Valid CeoTrainerRegisterRequest request) {
        return createBaseUser(request.username(), request.birthDate(), request.email(), request.password(), request.gender(), UserRoles.CEO_TRAINER);
    }

    private UserEntity buildBaseUser(@Valid PersonalRegisterRequest request) {
        return createBaseUser(request.username(), request.birthDate(), request.email(), request.password(), request.gender(), UserRoles.PERSONAL_TRAINER);
    }

    private void validateRequest(@Valid RegisterRequest request) {
        registerValidationsList.forEach(strategy -> strategy.registerResponseValidations(request));
    }

    private void validateCeoRequest(CeoTrainerRegisterRequest request) {
        registerCeoValidationsList.forEach(strategy -> strategy.registerCeoResponseValidations(request));
    }

    private void validatePersonalRequest(PersonalRegisterRequest request) {
        personalRegisterValidations.forEach(strategy -> strategy.personalRegisterValidations(request));
    }

    private int calculateAge(Date birthDate) {
        if (birthDate == null) return 0;
        java.time.LocalDate birthLocalDate = new java.sql.Date(birthDate.getTime()).toLocalDate();
        java.time.LocalDate today = java.time.LocalDate.now();
        return java.time.Period.between(birthLocalDate, today).getYears();
    }


}

