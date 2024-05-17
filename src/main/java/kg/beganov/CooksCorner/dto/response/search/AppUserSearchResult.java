package kg.beganov.CooksCorner.dto.response.search;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.MODULE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserSearchResult {
    Long userId;
    String userImagePath;
    String userName;
}
