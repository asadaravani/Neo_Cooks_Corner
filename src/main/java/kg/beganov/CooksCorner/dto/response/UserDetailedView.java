package kg.beganov.CooksCorner.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserDetailedView {
    Long id;
    String imagePath;
    String name;
    Long recipesCount;
    Long followersCount;
    Long followingsCount;
    String bio;
}
