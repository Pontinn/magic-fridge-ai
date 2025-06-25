package dev.pontin.MagicFridgeAI.controller;

import dev.pontin.MagicFridgeAI.model.FoodItemModel;
import dev.pontin.MagicFridgeAI.service.ChatGptTextService;
import dev.pontin.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class RecipeController {

    private final FoodItemService foodItemService;
    private final ChatGptTextService gptService;

    public RecipeController(FoodItemService foodItemService, ChatGptTextService gptService) {
        this.foodItemService = foodItemService;
        this.gptService = gptService;
    }

    @GetMapping("/gerar")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItemModel> foodList = foodItemService.listar();
        return gptService.generateRecipe(foodList)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
