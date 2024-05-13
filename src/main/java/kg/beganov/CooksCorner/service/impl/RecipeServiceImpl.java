package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.entity.Recipe;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.exception.ProductNotFoundException;
import kg.beganov.CooksCorner.repository.RecipeRepository;
import kg.beganov.CooksCorner.service.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeServiceImpl implements RecipeService {
    RecipeRepository recipeRepository;

    @Override
    public List<RecipePreview> getBreakfastRecipes(){
        return mapRecipeToPreview(recipeRepository.findAllByCategory(Category.BREAKFAST));
    }
    @Override
    public List<RecipePreview> getLunchRecipes(){
        return mapRecipeToPreview(recipeRepository.findAllByCategory(Category.LUNCH));
    }
    @Override
    public List<RecipePreview> getDinnerRecipes(){
        return mapRecipeToPreview(recipeRepository.findAllByCategory(Category.DINNER));
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
                .ingredients(recipe.getIngredients())
                .build();
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
}
