package gemini.FitGymGpt.Service.Groq;

import gemini.FitGymGpt.DTO.Groq.BodyStatsRequest;
import gemini.FitGymGpt.DTO.Groq.GroqChatRequest;
import gemini.FitGymGpt.DTO.Groq.GroqChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroqService {

    private final RestTemplate restTemplate;

    @Value("${groq.ai.api-url}")
    private String apiUrl;

    @Value("${groq.ai.api-key}")
    private String apiKey;

    public String generateGroqResponse(BodyStatsRequest request) {
        GroqChatRequest chatRequest = new GroqChatRequest();
        chatRequest.setModel("llama-3.1-8b-instant");
        chatRequest.setMessages(List.of(
                new GroqChatRequest.Message("system", "Você é um assistente de fitness especializado em criar planos de treino personalizados."),
                new GroqChatRequest.Message("user", formatPrompt(request))
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<GroqChatRequest> entity = new HttpEntity<>(chatRequest, headers);

        try {
            ResponseEntity<GroqChatResponse> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, entity, GroqChatResponse.class
            );

            if (response.getBody() != null &&
                    response.getBody().getChoices() != null &&
                    !response.getBody().getChoices().isEmpty()) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Erro ao obter resposta do Groq AI");
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
                  "terça": [...] isso tudo até sábado
                  
                  SEGUE APENAS O EXEMPLO, NÃO FOGE DO PADRÃO, NÃO COMENTE NADA A RESPEITO, APENAS SEGUE O EXEMPLO 
                }
                """,
                s.getAge(), s.getHeight(), s.getWeight(),
                s.getArm(), s.getChest(), s.getWaist(),
                s.getAbdomen(), s.getHip(), s.getThigh(),
                s.getCalf(), s.getBiotype());
    }
}
