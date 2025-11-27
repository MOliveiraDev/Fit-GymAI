package gemini.FitGymGpt.strategy.gemini.impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import org.springframework.stereotype.Component;

@Component
public class MedicalConditionsSectionStrategy implements IPromptSectionBuilder {

    @Override
    public String build(BodyStatsRequest stats) {
        return "- Condições médicas: " + String.join(", ", stats.medicalConditions()) + "\n";
    }

    @Override
    public boolean isApplicable(BodyStatsRequest stats) {
        return stats.medicalConditions() != null && !stats.medicalConditions().isEmpty();
    }
}