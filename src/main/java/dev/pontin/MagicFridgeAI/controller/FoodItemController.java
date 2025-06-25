package dev.pontin.MagicFridgeAI.controller;

import dev.pontin.MagicFridgeAI.model.FoodItemModel;
import dev.pontin.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    //GET
    @GetMapping("/list")
    public ResponseEntity<List<FoodItemModel>> listAll() {
        List<FoodItemModel> foodList = foodItemService.list();
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<FoodItemModel> listById(@PathVariable Long id) {
        FoodItemModel foodId = foodItemService.listById(id);
        return ResponseEntity.ok(foodId);
    }

    //POST
    @PostMapping("/save")
    public ResponseEntity<FoodItemModel> add(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel foodItemModelSave = foodItemService.save(foodItemModel);
        return ResponseEntity.ok()
                .body(foodItemModel);
    }

    //PUT
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody FoodItemModel foodItemModel) {
        if (foodItemService.listById(foodItemModel.getId()) != null) {
            foodItemService.update(foodItemModel);
            return ResponseEntity.ok("Ok!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(FoodItemModel foodItemModel) {
        if (foodItemService.listById(foodItemModel.getId()) != null) {
            foodItemService.delete(foodItemModel);
            return ResponseEntity.ok("Deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comida não encontrada!");
        }
    }
}
