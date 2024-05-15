package kg.beganov.CooksCorner.dto.request;

import kg.beganov.CooksCorner.dto.IngredientDto;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.enums.Difficulty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeRequest {
    Long authorId;
    String imagePath;
    String name;
    String description;
    List<IngredientDto> ingredients;
    Difficulty difficulty;
    Category category;
    String preparationTime;
}
