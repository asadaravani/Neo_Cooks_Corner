package kg.beganov.CooksCorner.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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
    BigDecimal likes;
    String description;
    String ingredients;

}
