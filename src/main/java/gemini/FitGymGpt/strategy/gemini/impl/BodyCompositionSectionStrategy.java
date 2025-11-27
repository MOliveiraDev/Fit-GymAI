package gemini.FitGymGpt.strategy.gemini.impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import org.springframework.stereotype.Component;

@Component
public class BodyCompositionSectionStrategy implements IPromptSectionBuilder {

    @Override
    public String build(BodyStatsRequest stats) {
        StringBuilder section = new StringBuilder("\n## COMPOSIÇÃO CORPORAL\n");

        if (stats.bodyFatPercentage() != null) {
            section.append(String.format("- Percentual de gordura: %.1f%%\n", stats.bodyFatPercentage()));
        }
        if (stats.muscleMass() != null) {
            section.append(String.format("- Massa muscular: %.2f kg\n", stats.muscleMass()));
        }

        return section.toString();
    }

    @Override
    public boolean isApplicable(BodyStatsRequest stats) {
        return stats.bodyFatPercentage() != null || stats.muscleMass() != null;
    }
}