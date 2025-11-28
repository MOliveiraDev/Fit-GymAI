package gemini.FitGymGpt.service.fitcenter;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.database.repository.fitcenter.GymCenterEntityRepository;
import gemini.FitGymGpt.dto.fitcenter.CreateGymCenterRequest;
import gemini.FitGymGpt.dto.fitcenter.CreateGymCenterResponse;
import gemini.FitGymGpt.strategy.fitcenter.IGymCenterValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GymCenterService {

    private final GymCenterEntityRepository gymCenterEntityRepository;
    private final List<IGymCenterValidations> gymCenterValidations;

    public CreateGymCenterResponse createGymCenter(CreateGymCenterRequest request) {
        gymCenterValidations.forEach(validation -> validation.gymCenterValidations(request));

        GymCenterEntity gym = new GymCenterEntity();
        gym.setGymCenterName(request.gymCenterName());
        gym.setGymDescription(request.gymDescription());
        gym.setGymPhone(request.gymPhone());
        gym.setGymEmail(request.gymEmail());
        gym.setGymCenterAddress(request.gymCenterAddress());
        gym.setGymWebsite(request.gymWebsite());
        gym.setOpeningTime(request.openingTime());
        gym.setClosingTime(request.closingTime());

        gymCenterEntityRepository.save(gym);
        return new CreateGymCenterResponse("A Academia foi criada");
    }

}
