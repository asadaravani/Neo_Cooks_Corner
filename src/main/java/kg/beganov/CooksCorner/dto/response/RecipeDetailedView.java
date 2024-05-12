package kg.beganov.CooksCorner.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDetailedView {
    Long id;
    String imagePath;
    String name;
    String preparationTime;
    String author;
    BigDecimal likes;
    String description;
    String ingredients;

}
