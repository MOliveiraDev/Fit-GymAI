
package gemini.FitGymGpt.dto.gemini;

import java.util.List;

public record GeminiChatRequest(
        List<Content> contents
) {
    public record Content(
            List<Part> parts
    ) {}

    public record Part(
            String text
    ) {}
}