package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;

import java.util.List;

public interface RecipeService {
    List<RecipePreview> getBreakfastRecipes();

    List<RecipePreview> getLunchRecipes();

    List<RecipePreview> getDinnerRecipes();

    RecipeDetailedView getRecipeById(Long id);
}
