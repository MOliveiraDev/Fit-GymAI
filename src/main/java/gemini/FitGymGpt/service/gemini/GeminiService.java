package gemini.FitGymGpt.service.gemini;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.dto.gemini.GeminiChatRequest;
import gemini.FitGymGpt.dto.gemini.GeminiChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestTemplate restTemplate;

    @Value("${gemini.ai.api-url}")
    private String apiUrl;

    @Value("${gemini.ai.api-key}")
    private String apiKey;

    public String workPlanGenerate(BodyStatsRequest request) {
        String prompt = formatPrompt(request);

        GeminiChatRequest.Content content = new GeminiChatRequest.Content(
                List.of(new GeminiChatRequest.Part(prompt))
        );
        GeminiChatRequest chatRequest = new GeminiChatRequest(List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GeminiChatRequest> entity = new HttpEntity<>(chatRequest, headers);

        try {
            ResponseEntity<GeminiChatResponse> response = restTemplate.exchange(
                    apiUrl + ":generateContent?key=" + apiKey,
                    HttpMethod.POST,
                    entity,
                    GeminiChatResponse.class
            );

            if (response.getBody() != null &&
                    !response.getBody().getCandidates().isEmpty()) {
                return response.getBody()
                        .getCandidates()
                        .get(0)
                        .getContent()
                        .getParts()
                        .get(0)
                        .getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Erro ao se comunicar com a Gemini API.";
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
                
               

                Exemplo:
                {
                  "segunda": [
                    {
                      "exercicio": "treino",
                      "series": numero de séries,
                      "repeticoes": numero de repetições,
                      "video": "link do vídeo"
                    }
                  ],
                  "terça": [...]
                  CADA DIA DEVE TER MAIS DE 5 EXERCÍCIOS.
                  SEGUE APENAS O EXEMPLO, NÃO FOGE DO PADRÃO, NÃO COMENTE NADA A RESPEITO, APENAS SEGUE O EXEMPLO.
                }
                """,
                s.getAge(), s.getHeight(), s.getWeight(),
                s.getArm(), s.getChest(), s.getWaist(),
                s.getAbdomen(), s.getHip(), s.getThigh(),
                s.getCalf(), s.getBiotype());
    }
}
