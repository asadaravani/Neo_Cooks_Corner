package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.request.RecipeRequest;
import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.Recipe;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.exception.ProductNotFoundException;
import kg.beganov.CooksCorner.mapper.RecipeMapper;
import kg.beganov.CooksCorner.repository.RecipeRepository;
import kg.beganov.CooksCorner.service.AppUserService;
import kg.beganov.CooksCorner.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeServiceImpl implements RecipeService {
    RecipeRepository recipeRepository;
    AppUserService appUserService;
    RecipeMapper recipeMapper;

    @Override
    public List<RecipePreview> getRecipesByCategory(Category category){
        return recipeMapper.mapRecipeToPreview(recipeRepository.findAllByCategory(category));
    }
    @Override
    public RecipeDetailedView getRecipeById(Long id){
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return recipeMapper.mapRecipeToDetailedView(recipe);
    }

    @Override
    public String addRecipe(RecipeRequest recipeRequest){
        Recipe recipe = new Recipe();
        AppUser user = appUserService.findById(recipeRequest.getAuthorId());
        recipeRepository.save(recipeMapper.mapRecipeRequestToRecipe(recipeRequest, recipe, user));
        return "Recipe added successfully";
    }
    @Override
    public String deleteRecipe(Long id){
        recipeRepository.deleteById(id);
        return "Recipe deleted successfully";
    }

    @Override
    public void saveRecipeByUser(Long recipeId, Long userId){
        Recipe recipe = findRecipeById(recipeId);
        AppUser user = appUserService.findById(userId);
        if(isRecipeSavedByUser(recipeId, userId)){
            recipe.getSavedByUsers().remove(user);
            user.getSavedRecipes().remove(recipe);
        }
        else {
            recipe.getSavedByUsers().add(user);
            user.getSavedRecipes().add(recipe);
        }
        recipeRepository.save(recipe);
        appUserService.save(user);
    }
    @Override
    public boolean isRecipeSavedByUser(Long recipeId, Long userId){
        Recipe recipe = findRecipeById(recipeId);
        return recipe.getSavedByUsers().stream().anyMatch(user -> user.getId().equals(userId));
    }

    @Override
    public void likeRecipeByUser(Long recipeId, Long userId){
        Recipe recipe = findRecipeById(recipeId);
        AppUser user = appUserService.findById(userId);
        if(isRecipeLikedByUser(recipeId, userId)){
            recipe.getLikedByUsers().remove(user);
            user.getLikedRecipes().remove(recipe);
        }
        else {
            recipe.getLikedByUsers().add(user);
            user.getLikedRecipes().add(recipe);
        }
        recipeRepository.save(recipe);
        appUserService.save(user);
    }
    @Override
    public boolean isRecipeLikedByUser(Long recipeId, Long userId){
        Recipe recipe = findRecipeById(recipeId);
        return recipe.getLikedByUsers().stream().anyMatch(user -> user.getId().equals(userId));
    }
    @Override
    public List<Recipe> findRecipesByNameContaining(String query){
        return recipeRepository.findByNameContainingIgnoreCase(query);
    }
    private Recipe findRecipeById(Long id){
        return recipeRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
