package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.IngredientDto;
import kg.beganov.CooksCorner.entity.Ingredient;
import kg.beganov.CooksCorner.entity.Recipe;

public interface IngredientService {
    Ingredient createIngredient(IngredientDto ingredientDto, Recipe recipe);
}
