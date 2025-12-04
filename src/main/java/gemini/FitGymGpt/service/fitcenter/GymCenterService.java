package gemini.FitGymGpt.service.fitcenter;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.database.repository.fitcenter.GymCenterRepository;
import gemini.FitGymGpt.dto.fitcenter.GymCenterRequest;
import gemini.FitGymGpt.dto.fitcenter.GymCenterResponse;
import gemini.FitGymGpt.enums.GymStatus;
import gemini.FitGymGpt.strategy.fitcenter.IGymCenterValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GymCenterService {

    private final GymCenterRepository gymCenterRepository;
    private final List<IGymCenterValidations> gymCenterValidations;

    public GymCenterResponse createGymCenter(GymCenterRequest request) {
        gymCenterValidations.forEach(validation -> validation.gymCenterValidations(request));

        GymCenterEntity gym = new GymCenterEntity();
        fitBuilder(request, gym);
        gym.setOpeningTime(request.openingTime());
        gym.setClosingTime(request.closingTime());

        gymCenterRepository.save(gym);
        return new GymCenterResponse("A Academia foi criada");
    }

    public GymCenterResponse updateGymCenter(Long gymCenterId, GymCenterRequest request) {
        gymCenterRepository.findById(gymCenterId).ifPresent(gym -> {
            fitBuilder(request, gym);
        });
        return new GymCenterResponse("A Academia foi atualizada");
    }

    private void fitBuilder(GymCenterRequest request, GymCenterEntity gym) {
        gym.setGymCenterName(request.gymCenterName());
        gym.setGymDescription(request.gymDescription());
        gym.setGymPhone(request.gymPhone());
        gym.setGymEmail(request.gymEmail());
        gym.setGymCenterAddress(request.gymCenterAddress());
        gym.setTaxId(request.taxId());
        gym.setGymImageUrl(request.gymImageUrl());
        gym.setGymWebsite(request.gymWebsite());
    }

    public Page<GymCenterEntity> findGymCenterByName(String gymCenterName, Pageable pageable) {
        return gymCenterRepository.findByGymCenterNameContainingIgnoreCase(gymCenterName, pageable);
    }

    public Page<GymCenterEntity> findByGymCenterOpenTime(LocalDate openingTime, Pageable pageable){
        return gymCenterRepository.findByOpeningTime(openingTime, pageable);
    }

    public Page<GymCenterEntity> findByGymCenterCloseTime(LocalDate closingTime, Pageable pageable){
        return gymCenterRepository.findByClosingTime(closingTime, pageable);
    }


    public Page<GymCenterEntity> findByGymCenterOpened(Pageable pageable) {
        return gymCenterRepository.findByGymStatus(GymStatus.OPEN, pageable);
    }

    public Page<GymCenterEntity> findByGymCenterExpired(Pageable pageable) {
        return gymCenterRepository.findByGymStatus(GymStatus.EXPIRED, pageable);
    }

    public Page<GymCenterEntity> findByGymCenterCanceled(Pageable pageable) {
        return gymCenterRepository.findByGymStatus(GymStatus.CANCELED, pageable);
    }
}
