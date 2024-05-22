package kg.beganov.CooksCorner.mapper;

import kg.beganov.CooksCorner.dto.IngredientDto;
import kg.beganov.CooksCorner.dto.request.RecipeRequest;
import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.dto.response.search.RecipeSearchResult;
import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.Ingredient;
import kg.beganov.CooksCorner.entity.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public List<RecipePreview> mapRecipeToPreview(List<Recipe> recipes){
        return recipes.stream()
                .map(recipe -> RecipePreview.builder()
                        .id(recipe.getId())
                        .imagePath(recipe.getImagePath())
                        .name(recipe.getName())
                        .author(recipe.getAppUser().getName())
                        .likes((long) recipe.getLikedByUsers().size())
                        .saves((long) recipe.getSavedByUsers().size())
                        .build())
                .collect(Collectors.toList());
    }
    public List<IngredientDto> mapIngredientsToDto(List<Ingredient> ingredients){
        return ingredients.stream()
                .map(ingredient -> IngredientDto.builder()
                            .key(ingredient.getKey())
                            .value(ingredient.getValue())
                            .build())
                .collect(Collectors.toList());
    }
    public List<Ingredient> mapIngredientDtoToIngredient(List<IngredientDto> dtos, Recipe recipe){
        return dtos.stream()
                .map(dto -> Ingredient.builder()
                        .key(dto.getKey())
                        .value(dto.getValue())
                        .recipe(recipe).build())
                .collect(Collectors.toList());

    }
    public List<RecipeSearchResult> mapRecipesToSearchResults(List<Recipe> recipes){
        return recipes.stream()
                .map(recipe -> RecipeSearchResult.builder()
                        .recipeId(recipe.getId())
                        .recipeImagePath(recipe.getImagePath())
                        .recipeName(recipe.getName()).build())
                .collect(Collectors.toList());
    }
    public RecipeDetailedView mapRecipeToDetailedView(Recipe recipe, boolean isLiked, boolean isSaved){
        return RecipeDetailedView.builder()
                .id(recipe.getId())
                .imagePath(recipe.getImagePath())
                .name(recipe.getName())
                .preparationTime(recipe.getPreparationTime())
                .authorId(recipe.getAppUser().getId())
                .author(recipe.getAppUser().getName())
                .likes((long) recipe.getLikedByUsers().size())
                .isLiked(isLiked)
                .isSaved(isSaved)
                .description(recipe.getDescription())
                .ingredients(mapIngredientsToDto(recipe.getIngredients()))
                .difficulty(recipe.getDifficulty())
                .build();
    }
    public Recipe mapRecipeRequestToRecipe(RecipeRequest recipeRequest, Recipe recipe, AppUser user) {
        recipe.setAppUser(user);
        recipe.setImagePath(recipeRequest.getImagePath());
        recipe.setName(recipeRequest.getName());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setIngredients(mapIngredientDtoToIngredient(recipeRequest.getIngredients(), recipe));
        recipe.setDifficulty(recipeRequest.getDifficulty());
        recipe.setCategory(recipeRequest.getCategory());
        recipe.setPreparationTime(recipeRequest.getPreparationTime());
        return recipe;
    }
}
