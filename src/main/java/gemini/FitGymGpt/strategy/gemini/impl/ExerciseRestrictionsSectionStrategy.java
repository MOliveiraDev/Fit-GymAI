package gemini.FitGymGpt.strategy.gemini.impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExerciseRestrictionsSectionStrategy implements IPromptSectionBuilder {

    @Override
    public String build(BodyStatsRequest stats) {
        return "\n## EXERCÍCIOS A EVITAR\n" +
                String.join(", ", stats.exerciseRestrictions()) + "\n";
    }

    @Override
    public boolean isApplicable(BodyStatsRequest stats) {
        return stats.exerciseRestrictions() != null && !stats.exerciseRestrictions().isEmpty();
    }
}