package gemini.FitGymGpt.controller.fitcenter.impl;

import gemini.FitGymGpt.controller.fitcenter.IFitCenterController;
import gemini.FitGymGpt.dto.fitcenter.GymCenterRequest;
import gemini.FitGymGpt.dto.fitcenter.GymCenterResponse;
import gemini.FitGymGpt.service.fitcenter.GymCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fitCenter")
public class FitCenterController implements IFitCenterController {

    private final GymCenterService gymCenterService;

    @Override
    public ResponseEntity<GymCenterResponse> createFitCenter(@Valid @RequestBody GymCenterRequest request) {
        GymCenterResponse response = gymCenterService.createGymCenter(request);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<GymCenterResponse> updateGymCenter(Long gymCenterId, @Valid @RequestBody GymCenterRequest request) {
        GymCenterResponse response = gymCenterService.updateGymCenter(gymCenterId, request);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<?> listGymCentersWithName(String name, int page, int size) {
        return ResponseEntity.status(200).body(gymCenterService.findGymCenterByName(name, PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<?> listOpenGymCenters(int page, int size) {
        return ResponseEntity.status(200).body(gymCenterService.findByGymCenterOpened(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<?> listExpiredGymCenters(int page, int size) {
        return ResponseEntity.status(200).body(gymCenterService.findByGymCenterExpired(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<?> listCanceledGymCenters(int page, int size) {
        return ResponseEntity.status(200).body(gymCenterService.findByGymCenterCanceled(PageRequest.of(page, size)));

    }
}
