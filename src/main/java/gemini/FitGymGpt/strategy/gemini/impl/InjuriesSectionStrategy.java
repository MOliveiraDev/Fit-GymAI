package gemini.FitGymGpt.strategy.gemini.impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import org.springframework.stereotype.Component;

@Component
public class InjuriesSectionStrategy implements IPromptSectionBuilder {

    @Override
    public String build(BodyStatsRequest stats) {
        StringBuilder section = new StringBuilder("\n## LESÕES E LIMITAÇÕES\n");

        if (stats.injuries() != null && !stats.injuries().isEmpty()) {
            section.append("- Lesões: ").append(String.join(", ", stats.injuries())).append("\n");
        }

        return section.toString();
    }

    @Override
    public boolean isApplicable(BodyStatsRequest stats) {
        return stats.injuries() != null && !stats.injuries().isEmpty();
    }
}