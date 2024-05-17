package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.response.search.SearchResults;
import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.Recipe;
import kg.beganov.CooksCorner.exception.SearchingErrorException;
import kg.beganov.CooksCorner.mapper.AppUserMapper;
import kg.beganov.CooksCorner.mapper.RecipeMapper;
import kg.beganov.CooksCorner.service.AppUserService;
import kg.beganov.CooksCorner.service.RecipeService;
import kg.beganov.CooksCorner.service.SearchPageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SearchPageServiceImpl implements SearchPageService {
    RecipeService recipeService;
    AppUserService appUserService;
    AppUserMapper userMapper;
    RecipeMapper recipeMapper;

    @Override
    public SearchResults searchByQuery(String query) throws SearchingErrorException {
        try{
            List<AppUser> users = appUserService.findAppUsersByNameContaining(query);
            List<Recipe> recipes = recipeService.findRecipesByNameContaining(query);
            return SearchResults.builder()
                    .recipeSearchResults(recipeMapper.mapRecipesToSearchResults(recipes))
                    .userSearchResults(userMapper.mapAppUsersToSearchResults(users))
                    .build();
        }catch (SearchingErrorException e){
            throw new SearchingErrorException();
        }

    }
}
