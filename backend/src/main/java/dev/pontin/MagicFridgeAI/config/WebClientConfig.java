package dev.pontin.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${chatgpt.api.url:https://api.openai.com/v1}")
    private String chatGptApiUrl;

    @Primary
    @Bean("recipeWebClient")
    @Qualifier("recipeApi")
    WebClient webClientRecipe(WebClient.Builder builder) {
        return builder.baseUrl(chatGptApiUrl + "/responses")
                .build();
    }

    @Bean("imageWebClient")
    @Qualifier("imageApi")
    WebClient webClientImage(WebClient.Builder builder) {
        return builder.baseUrl(chatGptApiUrl + "/images/generations")
                .build();
    }

}
