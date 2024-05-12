package kg.beganov.CooksCorner.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipePreview {
    Long id;
    String imagePath;
    String name;
    String author;
    BigDecimal likes;
    BigDecimal saves;
}
