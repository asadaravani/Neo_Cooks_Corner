package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.request.RecipeRequest;
import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.enums.Category;

import java.util.List;

public interface RecipeService {
    List<RecipePreview> getRecipesByCategory(Category category);

    RecipeDetailedView getRecipeById(Long id);

    String addRecipe(RecipeRequest recipeRequest);

    String deleteRecipe(Long id);
}
