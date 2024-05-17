package kg.beganov.CooksCorner.service;

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

    String uploadUserImage(MultipartFile file) throws IOException;

    void follow(Long followerId, Long followingId);

    List<RecipePreview> getUserRecipesById(Long userId);

    void save(AppUser user);

    List<AppUser> findAppUsersByNameContaining(String query);
}
