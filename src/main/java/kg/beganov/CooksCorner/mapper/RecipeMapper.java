package kg.beganov.CooksCorner.mapper;

import kg.beganov.CooksCorner.dto.IngredientDto;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.dto.response.search.RecipeSearchResult;
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
}
