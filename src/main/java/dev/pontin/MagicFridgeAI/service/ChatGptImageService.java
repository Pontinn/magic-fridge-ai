package dev.pontin.MagicFridgeAI.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptImageService {

    private final WebClient webClient;
    private final String apiKey = System.getenv("API_KEY");

    public ChatGptImageService(@Qualifier("imageApi") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> imageGenerate(String recipe) {
        String prompt = "Crie uma imagem de acordo com o titulo da receita, a imagem precisa ser bem fiel ao titulo e precisa conter todos os ingredientes. Receita: \r\n" + recipe;

        Map<String, Object> requestBody = Map.of(
                "model", "dall-e-3",
                "prompt", prompt,
                "n", 1,
                "size", "1024x1024"
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var data = (List<Map<String, Object>>) response.get("data");
                    if (data != null && !data.isEmpty()) {
                        return (String) data.getFirst().get("url");
                    }
                    return "Nenhuma imagem foi gerada.";
                });
    }
}
