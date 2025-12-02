package gemini.FitGymGpt.controller.fitcenter.impl;

import gemini.FitGymGpt.controller.fitcenter.IFitCenterController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fitcenter")
public class FitCenterController implements IFitCenterController {
}
