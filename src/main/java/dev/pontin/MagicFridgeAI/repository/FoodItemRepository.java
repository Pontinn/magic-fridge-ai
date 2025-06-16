package dev.pontin.MagicFridgeAI.repository;

import dev.pontin.MagicFridgeAI.model.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemModel, Long> {
}
