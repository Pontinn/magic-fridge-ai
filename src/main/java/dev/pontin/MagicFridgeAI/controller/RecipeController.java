package dev.pontin.MagicFridgeAI.controller;

import dev.pontin.MagicFridgeAI.model.FoodItemModel;
import dev.pontin.MagicFridgeAI.service.ChatGptImageService;
import dev.pontin.MagicFridgeAI.service.ChatGptTextService;
import dev.pontin.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final FoodItemService foodItemService;
    private final ChatGptTextService chatGptTextService;
    private final ChatGptImageService chatGptImageService;

    public RecipeController(FoodItemService foodItemService, ChatGptTextService chatGptTextService, ChatGptImageService chatGptImageService) {
        this.foodItemService = foodItemService;
        this.chatGptTextService = chatGptTextService;
        this.chatGptImageService = chatGptImageService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItemModel> foodList = foodItemService.list();
        return chatGptTextService.generateRecipe(foodList)
                .flatMap(generatedRecipe -> {
                    return chatGptImageService.imageGenerate(generatedRecipe)
                            .map(generatedImage -> {
                                String responseBody = "Receita gerada: " + generatedRecipe + "\r\n Imagem da receita: " + generatedImage;
                                return ResponseEntity.ok(responseBody);
                            });
                });
    }
}
