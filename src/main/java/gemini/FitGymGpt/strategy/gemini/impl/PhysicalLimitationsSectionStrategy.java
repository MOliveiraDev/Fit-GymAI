package gemini.FitGymGpt.strategy.gemini.impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import org.springframework.stereotype.Component;

@Component
public class PhysicalLimitationsSectionStrategy implements IPromptSectionBuilder {

    @Override
    public String build(BodyStatsRequest stats) {
        return "- Limitações físicas: " + String.join(", ", stats.physicalLimitations()) + "\n";
    }

    @Override
    public boolean isApplicable(BodyStatsRequest stats) {
        return stats.physicalLimitations() != null && !stats.physicalLimitations().isEmpty();
    }
}