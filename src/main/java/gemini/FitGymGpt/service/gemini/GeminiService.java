
package gemini.FitGymGpt.service.gemini;

import gemini.FitGymGpt.dto.gemini.BodyStatsRequest;
import gemini.FitGymGpt.dto.gemini.GeminiChatRequest;
import gemini.FitGymGpt.dto.gemini.GeminiChatResponse;
import gemini.FitGymGpt.exception.gemini.GeminiApiException;
import gemini.FitGymGpt.strategy.gemini.IPromptSectionBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiService {

    private static final String GENERATE_CONTENT_ENDPOINT = ":generateContent?key=";
    private static final String ERROR_MESSAGE = "Erro ao gerar o plano de treino. Tente novamente mais tarde.";

    private final RestTemplate restTemplate;
    private final List<IPromptSectionBuilder> sectionBuilders;

    @Value("${gemini.ai.api-url}")
    private String apiUrl;

    @Value("${gemini.ai.api-key}")
    private String apiKey;

    public String generateWorkoutPlan(BodyStatsRequest request) {
        log.info("Iniciando geração de plano de treino para usuário");

        try {
            String prompt = buildWorkoutPrompt(request);
            GeminiChatRequest chatRequest = createChatRequest(prompt);
            HttpEntity<GeminiChatRequest> entity = createHttpEntity(chatRequest);

            ResponseEntity<GeminiChatResponse> response = callGeminiApi(entity);

            return extractTextFromResponse(response);

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Erro HTTP ao chamar API Gemini: {}", e.getMessage());
            throw new GeminiApiException("Erro na comunicação com a API: " + e.getStatusCode());
        } catch (Exception e) {
            log.error("Erro inesperado ao gerar plano de treino", e);
            throw new GeminiApiException(ERROR_MESSAGE);
        }
    }

    private GeminiChatRequest createChatRequest(String prompt) {
        GeminiChatRequest.Content content = new GeminiChatRequest.Content(
                List.of(new GeminiChatRequest.Part(prompt))
        );
        return new GeminiChatRequest(List.of(content));
    }

    private HttpEntity<GeminiChatRequest> createHttpEntity(GeminiChatRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(chatRequest, headers);
    }

    private ResponseEntity<GeminiChatResponse> callGeminiApi(HttpEntity<GeminiChatRequest> entity) {
        String url = apiUrl + GENERATE_CONTENT_ENDPOINT + apiKey;
        log.debug("Chamando API Gemini: {}", apiUrl);

        return restTemplate.exchange(url, HttpMethod.POST, entity, GeminiChatResponse.class);
    }

    private String extractTextFromResponse(ResponseEntity<GeminiChatResponse> response) {
        if (response.getBody() == null || response.getBody().candidates().isEmpty()) {
            log.warn("Resposta vazia ou sem candidatos da API Gemini");
            throw new GeminiApiException(ERROR_MESSAGE);
        }

        try {
            String text = response.getBody()
                    .candidates()
                    .getFirst()
                    .content()
                    .parts()
                    .getFirst()
                    .text();

            log.info("Plano de treino gerado com sucesso");
            return text;

        } catch (Exception e) {
            log.error("Erro ao extrair texto da resposta: {}", e.getMessage());
            throw new GeminiApiException("Formato de resposta inválido da API");
        }
    }

    private String buildWorkoutPrompt(BodyStatsRequest stats) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Gere um plano de treino semanal PERSONALIZADO baseado nos seguintes dados:\n\n");
        prompt.append(buildPersonalData(stats));
        prompt.append(buildBodyMeasurements(stats));

        sectionBuilders.stream()
                .filter(builder -> builder.isApplicable(stats))
                .forEach(builder -> prompt.append(builder.build(stats)));

        prompt.append(buildGoalsAndExperience(stats));
        prompt.append(buildFormatInstructions());

        return prompt.toString();
    }

    private String buildPersonalData(BodyStatsRequest stats) {
        return String.format("""
                ## DADOS PESSOAIS
                - Idade: %d anos
                - Gênero: %s
                - Altura: %.2f m
                - Peso: %.2f kg
                - Biotipo: %s
                
                """,
                stats.age(),
                stats.gender(),
                stats.height(),
                stats.weight(),
                stats.biotype()
        );
    }

    private String buildBodyMeasurements(BodyStatsRequest stats) {
        return String.format("""
                ## MEDIDAS CORPORAIS
                **Membros Superiores:**
                  - Braço esquerdo: %.2f cm | Braço direito: %.2f cm
                  - Antebraço esquerdo: %.2f cm | Antebraço direito: %.2f cm
                
                **Tronco:**
                  - Ombros: %.2f cm
                  - Peito: %.2f cm
                  - Cintura: %.2f cm
                  - Abdômen: %.2f cm
                  - Quadril: %.2f cm
                
                **Membros Inferiores:**
                  - Coxa esquerda: %.2f cm | Coxa direita: %.2f cm
                  - Panturrilha esquerda: %.2f cm | Panturrilha direita: %.2f cm
                
                """,
                stats.leftArm(), stats.rightArm(),
                stats.leftForearm(), stats.rightForearm(),
                stats.shoulders(),
                stats.chest(),
                stats.waist(),
                stats.abdomen(),
                stats.hip(),
                stats.leftThigh(), stats.rightThigh(),
                stats.leftCalf(), stats.rightCalf()
        );
    }

    private String buildGoalsAndExperience(BodyStatsRequest stats) {
        return String.format("""
                
                ## OBJETIVOS E EXPERIÊNCIA
                - Objetivo: %s
                - Nível de experiência: %s
                - Dias disponíveis: %d por semana
                - Horário preferido: %s
                
                """,
                stats.fitnessGoal(),
                stats.experienceLevel(),
                stats.weeklyWorkoutDays(),
                stats.preferredWorkoutTime()
        );
    }

    private String buildFormatInstructions() {
        return """
                
                ## FORMATO DE RESPOSTA
                
                Gere um plano estruturado com:
                - Divisão de treinos por dia da semana
                - Mínimo de 5 exercícios por dia
                - Séries, repetições e tempo de descanso
                - Link de vídeo demonstrativo quando possível
                - Observações sobre execução e segurança
                
                **IMPORTANTE:**
                - Respeite TODAS as lesões e limitações mencionadas
                - Adapte os exercícios ao nível de experiência
                - Considere os equipamentos disponíveis
                - Evite exercícios listados nas restrições
                
                **FORMATO JSON (siga EXATAMENTE este padrão):**
                ```json
                {
                  "segunda": [
                    {
                      "exercicio": "Nome do exercício",
                      "series": 3,
                      "repeticoes": "12-15",
                      "descanso": "60s",
                      "video": "https://...",
                      "observacoes": "Dicas de execução"
                    }
                  ],
                  "terca": [...],
                  "quarta": [...],
                  "quinta": [...],
                  "sexta": [...],
                  "sabado": [...],
                  "domingo": [
                    {
                      "exercicio": "Descanso ou atividade leve",
                      "observacoes": "Recuperação ativa"
                    }
                  ]
                }
                ```
                
                NÃO adicione comentários ou texto extra. APENAS o JSON.
                """;
    }
}