package dev.pontin.MagicFridgeAI.service;

import dev.pontin.MagicFridgeAI.model.FoodItemModel;
import dev.pontin.MagicFridgeAI.repository.FoodItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemModel save(FoodItemModel foodItemModel) {
        return foodItemRepository.save(foodItemModel);
    }

    public List<FoodItemModel> list() {
        return foodItemRepository.findAll();
    }

    public FoodItemModel listById(Long id) {
        Optional<FoodItemModel> foodId = foodItemRepository.findById(id);
        return foodId.orElse(null);
    }

    public FoodItemModel update(FoodItemModel foodItemModel) {
        Optional<FoodItemModel> foodItemModelOptional = foodItemRepository.findById(foodItemModel.getId());
        if (foodItemModelOptional.isPresent()) {
            foodItemModel.setId(foodItemModel.getId());
            return foodItemRepository.save(foodItemModel);
        } else {
            throw new EntityNotFoundException("Comida com ID: " + foodItemModel.getId() + " não encontrado.");
        }
    }

    public void delete(FoodItemModel foodItemModel) {
        Optional<FoodItemModel> foodItemModelOptional = foodItemRepository.findById(foodItemModel.getId());
        if (foodItemModelOptional.isPresent()) {
            foodItemRepository.deleteById(foodItemModel.getId());
        } else {
            throw new EntityNotFoundException("Comida com ID: " + foodItemModel.getId() + " não encontrado.");
        }
    }
}
