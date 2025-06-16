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
    @GetMapping("/listar")
    public ResponseEntity<List<FoodItemModel>> listarTodos() {
        List<FoodItemModel> foodList = foodItemService.listar();
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<FoodItemModel> listarId(@PathVariable Long id) {
        FoodItemModel foodId = foodItemService.listarId(id);
        return ResponseEntity.ok(foodId);
    }

    //POST
    @PostMapping("/adicionar")
    public ResponseEntity<FoodItemModel> adicionar(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel foodItemModelSave = foodItemService.salvar(foodItemModel);
        return ResponseEntity.ok()
                .body(foodItemModel);
    }

    //PUT
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody FoodItemModel foodItemModel) {
        if (foodItemService.listarId(foodItemModel.getId()) != null) {
            foodItemService.atualizar(foodItemModel);
            return ResponseEntity.ok("Ok!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

    //DELETE
    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(FoodItemModel foodItemModel) {
        if (foodItemService.listarId(foodItemModel.getId()) != null) {
            foodItemService.deletar(foodItemModel);
            return ResponseEntity.ok("Deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comida não encontrada!");
        }
    }
}
