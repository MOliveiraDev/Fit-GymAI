package gemini.FitGymGpt.strategy.gemini.impl;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import org.springframework.stereotype.Component;

@Component
public class EquipmentSectionStrategy implements IPromptSectionBuilder {

    @Override
    public String build(BodyStatsRequest stats) {
        return "\n## EQUIPAMENTOS DISPONÍVEIS\n" +
                String.join(", ", stats.availableEquipment()) + "\n";
    }

    @Override
    public boolean isApplicable(BodyStatsRequest stats) {
        return stats.availableEquipment() != null && !stats.availableEquipment().isEmpty();
    }
}