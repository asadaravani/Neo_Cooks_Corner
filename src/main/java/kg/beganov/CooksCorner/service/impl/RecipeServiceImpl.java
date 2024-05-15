package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.IngredientDto;
import kg.beganov.CooksCorner.dto.request.RecipeRequest;
import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.entity.Ingredient;
import kg.beganov.CooksCorner.entity.Recipe;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.exception.ProductNotFoundException;
import kg.beganov.CooksCorner.repository.RecipeRepository;
import kg.beganov.CooksCorner.service.AppUserService;
import kg.beganov.CooksCorner.service.IngredientService;
import kg.beganov.CooksCorner.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeServiceImpl implements RecipeService {
    RecipeRepository recipeRepository;
    AppUserService appUserService;
    IngredientService ingredientService;

    @Override
    public List<RecipePreview> getRecipesByCategory(Category category){
        return mapRecipeToPreview(recipeRepository.findAllByCategory(category));
    }
    @Override
    public RecipeDetailedView getRecipeById(Long id){
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return RecipeDetailedView.builder()
                .id(recipe.getId())
                .imagePath(recipe.getImagePath())
                .name(recipe.getName())
                .preparationTime(recipe.getPreparationTime())
                .author(recipe.getAppUser().getName())
                .likes(recipe.getLikes())
                .description(recipe.getDescription())
                .ingredients(mapIngredientsToDto(recipe.getIngredients()))
                .build();
    }
    @Override
    public String addRecipe(RecipeRequest recipeRequest){
        Recipe recipe = new Recipe();
        recipe.setAppUser(appUserService.findById(recipeRequest.getAuthorId()));
        recipe.setImagePath(recipeRequest.getImagePath());
        recipe.setName(recipeRequest.getName());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setIngredients(mapIngredientDtoToIngredient(recipeRequest.getIngredients(), recipe));
        recipe.setDifficulty(recipeRequest.getDifficulty());
        recipe.setCategory(recipeRequest.getCategory());
        recipe.setPreparationTime(recipeRequest.getPreparationTime());
        recipeRepository.save(recipe);
        return "Recipe added successfully";
    }
    @Override
    public String deleteRecipe(Long id){
        recipeRepository.deleteById(id);
        return "Recipe deleted successfully";
    }

    private List<RecipePreview> mapRecipeToPreview(List<Recipe> recipes){
        List<RecipePreview> previews = new ArrayList<>();
        for (Recipe recipe : recipes) {
            RecipePreview preview = new RecipePreview();
            preview.setId(recipe.getId());
            preview.setImagePath(recipe.getImagePath());
            preview.setName(recipe.getName());
            preview.setAuthor(recipe.getAppUser().getName());
            preview.setLikes(recipe.getLikes());
            preview.setSaves(recipe.getSaves());
            previews.add(preview);
        }
        return previews;
    }
    private List<IngredientDto> mapIngredientsToDto(List<Ingredient> ingredients){
        List<IngredientDto> dtos = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientDto dto = new IngredientDto();
            dto.setKey(ingredient.getKey());
            dto.setValue(ingredient.getValue());
            dtos.add(dto);
        }
        return dtos;
    }
    private List<Ingredient> mapIngredientDtoToIngredient(List<IngredientDto> dtos, Recipe recipe){
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDto dto : dtos) {
            ingredients.add(ingredientService.createIngredient(dto, recipe));
        }
        return ingredients;
    }
}
