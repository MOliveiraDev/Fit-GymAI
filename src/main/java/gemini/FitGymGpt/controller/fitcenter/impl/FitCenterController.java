package gemini.FitGymGpt.controller.fitcenter.impl;

import gemini.FitGymGpt.controller.fitcenter.IFitCenterController;
import gemini.FitGymGpt.dto.fitcenter.GymCenterRequest;
import gemini.FitGymGpt.dto.fitcenter.GymCenterResponse;
import gemini.FitGymGpt.service.fitcenter.GymCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fitcenter")
public class FitCenterController implements IFitCenterController {

    private final GymCenterService gymCenterService;

    @Override
    public ResponseEntity<GymCenterResponse> createFitCenter(@Valid @RequestBody GymCenterRequest request) {
        GymCenterResponse response = gymCenterService.createGymCenter(request);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<GymCenterResponse> updateFitCenter(@PathVariable Long gymCenterId, @RequestBody GymCenterRequest request) {
        GymCenterResponse response = gymCenterService.updateGymCenter(gymCenterId, request);
        return ResponseEntity.status(201).body(response);
    }
}
