package kg.beganov.CooksCorner.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.beganov.CooksCorner.dto.request.RecipeRequest;
import kg.beganov.CooksCorner.dto.response.RecipeDetailedView;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.service.CloudinaryService;
import kg.beganov.CooksCorner.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {
    RecipeService recipeService;
    CloudinaryService cloudinaryService;

    @Operation(summary = "Get recipes by category")
    @GetMapping("/category")
    public List<RecipePreview> getBreakfastRecipes(Category category) {
        return recipeService.getRecipesByCategory(category);
    }

    @Operation(summary = "Get recipe by ID", description = "It provides a detailed recipe")
    @GetMapping("/{id}")
    public RecipeDetailedView getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @Operation(summary = "ADD recipe", description = "Difficulty: EASY, MEDIUM, HARD")
    @PostMapping("/addRecipe")
    public String addRecipe(@RequestBody RecipeRequest recipeRequest) {
        return recipeService.addRecipe(recipeRequest);
    }

    @Operation(summary = "Upload Image, max size : 1MB")
    @PostMapping(value = "/uploadRecipeImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadRecipeImage(@RequestParam("file") MultipartFile file) throws IOException {
        return cloudinaryService.uploadImage(file);
    }

    @Operation(summary = "Delete recipe by ID")
    @DeleteMapping("{id}")
    public String deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }

    @Operation(summary = "Delete recipe image by URL")
    @DeleteMapping("/deleteRecipeImage")
    public void deleteRecipeImage(String imageURL) throws IOException {
        cloudinaryService.deleteProductImage(imageURL);
    }
    /*
    ---------------------------------------------------------------------------------------------
     */
    @Operation(summary = "Save recipe by User", description = "It saves/removes")
    @PostMapping("/{recipeId}/save")
    public void saveRecipeByUser(@PathVariable Long recipeId, @RequestParam Long userId){
        recipeService.saveRecipeByUser(recipeId, userId);
    }
    @Operation(summary = "Is recipe saved by User?", description = "It is to on/off the 'SAVE' button")
    @GetMapping("/{recipeId}/isSaved")
    public boolean isRecipeSavedByUser(@PathVariable Long recipeId, @RequestParam Long userId){
        return recipeService.isRecipeSavedByUser(recipeId, userId);
    }
    /*
    ---------------------------------------------------------------------------------------------
     */
    @Operation(summary = "Like recipe by User", description = "It likes/removes")
    @PostMapping("/{recipeId}/like")
    public void likeRecipeByUser(@PathVariable Long recipeId, @RequestParam Long userId){
        recipeService.likeRecipeByUser(recipeId, userId);
    }
    @Operation(summary = "Is recipe liked by User?", description = "It is to on/off the 'Like' button")
    @GetMapping("/{recipeId}/isLiked")
    public boolean isRecipeLikedByUser(@PathVariable Long recipeId, @RequestParam Long userId){
        return recipeService.isRecipeLikedByUser(recipeId, userId);
    }



}
