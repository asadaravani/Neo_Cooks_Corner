package kg.beganov.CooksCorner.dto.response;

import kg.beganov.CooksCorner.dto.IngredientDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class RecipeDetailedView {
    Long id;
    String imagePath;
    String name;
    String preparationTime;
    String author;
    Long likes;
    String description;
    List<IngredientDto> ingredients;

}
