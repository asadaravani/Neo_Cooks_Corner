package kg.beganov.CooksCorner.controller;

import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/breakfast")
    public List<RecipePreview> getBreakfastRecipes() {
        return recipeService.getBreakfastRecipes();
    }

    @GetMapping("/lunch")
    public List<RecipePreview> getLunchRecipes() {
        return recipeService.getLunchRecipes();
    }

    @GetMapping("/dinner")
    public List<RecipePreview> getDinnerRecipes() {
        return recipeService.getDinnerRecipes();
    }

//    @GetMapping
//    public RecipeDetailedView getRecipeById(@RequestParam Long id) {
//        return recipeService.getRecipeById(id);
//    }
}
