package kg.beganov.CooksCorner.dto.response.search;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.MODULE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResults {
    List<RecipeSearchResult> recipeSearchResults;
    List<AppUserSearchResult> userSearchResults;
}
