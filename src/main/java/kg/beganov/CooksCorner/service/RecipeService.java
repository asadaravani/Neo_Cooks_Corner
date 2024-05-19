package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.request.RecipeRequest;
import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.entity.Recipe;
import kg.beganov.CooksCorner.enums.Category;
import java.util.List;

public interface RecipeService {
    List<RecipePreview> getRecipesByCategory(Category category);

    RecipeDetailedView getRecipeById(Long id);

    String addRecipe(RecipeRequest recipeRequest);

    String deleteRecipe(Long id);

    void saveRecipeByUser(Long recipeId, Long userId);

    boolean isRecipeSavedByUser(Long recipeId, Long userId);

    void likeRecipeByUser(Long userId, Long recipeId);

    boolean isRecipeLikedByUser(Long recipeId, Long userId);

    List<Recipe> findRecipesByNameContaining(String query);
}
