package gemini.FitGymGpt.Service.Gemini;

import gemini.FitGymGpt.DTO.Gemini.BodyStatsRequest;
import gemini.FitGymGpt.DTO.Gemini.GeminiRequest;
import gemini.FitGymGpt.DTO.Gemini.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.ai.api-url}")
    private String apiUrl;

    @Value("${gemini.ai.api-key}")
    private String apiKey;

    public String generateWorkoutPlan (BodyStatsRequest request) {
        String prompt = formatPrompt(request);

        GeminiRequest.Part part = new GeminiRequest.Part(prompt);
        GeminiRequest.Content content = new GeminiRequest.Content(List.of(part));
        GeminiRequest geminiRequest = new GeminiRequest(List.of(content));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GeminiRequest> entity = new HttpEntity<>(geminiRequest, httpHeaders);
        String fullUrl = apiUrl + apiKey;

        try{
            GeminiResponse response = new RestTemplate().postForObject(fullUrl, entity, GeminiResponse.class);
            if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
                List<GeminiResponse.Candidate> candidates = response.getCandidates();
                if (candidates.getFirst().getContents() != null) {
                    List<GeminiResponse.Content> contents = candidates.getFirst().getContents();
                    if (!contents.isEmpty() && contents.getFirst().getParts() != null) {
                        return contents.getFirst().getParts().getFirst().getText();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Desculpe, não consegui gerar um plano de treino no momento. Tente novamente mais tarde.";
    }

    private String formatPrompt(BodyStatsRequest s) {
        return String.format("""
                        Gere um plano de treino semanal baseado nos seguintes dados físicos:
                        - Idade: %d anos
                        - Altura: %.2f m
                        - Peso: %.2f kg
                        - Braço: %.2f cm
                        - Peito: %.2f cm
                        - Cintura: %.2f cm
                        - Abdômen: %.2f cm
                        - Quadril: %.2f cm
                        - Coxa: %.2f cm
                        - Panturrilha: %.2f cm
                        - Biotipo: %s

                        Gere um plano com:
                        - Dias da semana e treinos específicos
                        - Exercícios por dia
                        - Número de séries e repetições
                        - URLs de vídeos explicativos

                        Formato da resposta: JSON
                        Exemplo:
                        {
                          "segunda": [
                            {
                              "exercicio": "Supino reto",
                              "series": 4,
                              "repeticoes": 10,
                              "video": "https://www.youtube.com/watch?v=abc123"
                            }
                          ],
                          "terça": [...]
                        }
                        """,
                s.getAge(), s.getHeight(), s.getWeight(),
                s.getArm(), s.getChest(), s.getWaist(),
                s.getAbdomen(), s.getHip(), s.getThigh(), s.getCalf(), s.getBiotype());
    }
}