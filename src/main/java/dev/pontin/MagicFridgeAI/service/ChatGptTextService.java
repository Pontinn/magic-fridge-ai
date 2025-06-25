package dev.pontin.MagicFridgeAI.service;

import dev.pontin.MagicFridgeAI.model.FoodItemModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptTextService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptTextService(@Qualifier("recipeApi") WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<String> generateRecipe(List<FoodItemModel> foodList) {
        String alimentos = foodList.stream()
                .map(foodItemModel -> String.format("%s (%S) - Quantidade: %d, Validade: %s \r\n",
                        foodItemModel.getName(), foodItemModel.getFoodCategory(), foodItemModel.getQuantidade(), foodItemModel.getValidade()))
                .collect(Collectors.joining());

        String prompt = "Me sugira uma receita que contenha apenas os alimentos listados: \r\n" + alimentos + " Não use ingredientes que não estejam listados!";
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4.1",
                "instructions", "Fale como um chef de cozinha renomado que vai sugerir receitas com tom de humor",
                "input", prompt
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    @SuppressWarnings("unchecked")
                    var output = (List<Map<String, Object>>) response.get("output");
                    if (output != null && !output.isEmpty()) {
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> content = (List<Map<String, Object>>) output.get(0).get("content");
                        return (String) content.getFirst().get("text");
                    }
                    return "Nenhuma receita foi gerada.";
                });

    }
}
