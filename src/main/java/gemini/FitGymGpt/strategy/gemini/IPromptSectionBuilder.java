package gemini.FitGymGpt.strategy.gemini;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;

public interface IPromptSectionBuilder {

    String build(BodyStatsRequest stats);

    boolean isApplicable(BodyStatsRequest stats);
}
