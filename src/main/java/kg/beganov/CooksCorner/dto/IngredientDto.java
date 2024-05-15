package kg.beganov.CooksCorner.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientDto {
    String key;
    String value;
}
