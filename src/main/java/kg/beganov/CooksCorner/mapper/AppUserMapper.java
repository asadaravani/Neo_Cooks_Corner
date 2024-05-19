package kg.beganov.CooksCorner.mapper;

import kg.beganov.CooksCorner.dto.request.AppUserEditRequest;
import kg.beganov.CooksCorner.dto.response.UserDetailedView;
import kg.beganov.CooksCorner.dto.response.search.AppUserSearchResult;
import kg.beganov.CooksCorner.entity.AppUser;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserMapper {

    public UserDetailedView mapUserToDetailedView(AppUser user){
        return UserDetailedView.builder()
                .id(user.getId()).imagePath(user.getAvatarPath()).name(user.getName())
                .recipesCount((long)user.getRecipes().size())
                .followersCount((long)user.getFollowers().size())
                .followingsCount((long)user.getFollowings().size())
                .bio(user.getBio())
                .build();
    }
    public List<AppUserSearchResult> mapAppUsersToSearchResults(List<AppUser> appUsers){
        return appUsers.stream()
                .map(appUser -> AppUserSearchResult.builder()
                        .userId(appUser.getId())
                        .userImagePath(appUser.getAvatarPath())
                        .userName(appUser.getName()).build())
                .collect(Collectors.toList());
    }
    public AppUser mapEditRequestToAppUser(AppUserEditRequest editRequest, AppUser user){
        user.setName(editRequest.getEditedName());
        user.setBio(editRequest.getEditedBio());
        user.setAvatarPath(editRequest.getEditedAvatarPath());
        return user;
    }

}
