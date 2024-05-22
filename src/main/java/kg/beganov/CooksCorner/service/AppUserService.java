package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.request.AppUserEditRequest;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.dto.response.UserDetailedView;
import kg.beganov.CooksCorner.entity.AppUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AppUserService {
    AppUser findById(Long id);

    UserDetailedView getUserByEmail(String email);

    UserDetailedView getUserById(Long id);

    String editAppUserProfile(Long userId, AppUserEditRequest appUserEditRequest);

    String uploadUserImage(MultipartFile file) throws IOException;

    List<RecipePreview> getSavedRecipesById(Long userId);

    void followAppUser2AppUser(Long followerId, Long followingId);

    List<RecipePreview> getUserRecipesById(Long userId);

    boolean isAppUserFollowing2AppUser(Long followerId, Long followingId);

    void save(AppUser user);

    List<AppUser> findAppUsersByNameContaining(String query);
}
