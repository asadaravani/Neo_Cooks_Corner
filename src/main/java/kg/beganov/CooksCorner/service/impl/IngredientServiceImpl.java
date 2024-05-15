package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.IngredientDto;
import kg.beganov.CooksCorner.entity.Ingredient;
import kg.beganov.CooksCorner.entity.Recipe;
import kg.beganov.CooksCorner.service.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Override
    public Ingredient createIngredient(IngredientDto ingredientDto, Recipe recipe) {
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        ingredient.setKey(ingredientDto.getKey());
        ingredient.setValue(ingredientDto.getValue());
        return ingredient;
    }
}
