package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.dto.response.UserDetailedView;
import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.AppUserFollow;
import kg.beganov.CooksCorner.exception.UserNotFoundException;
import kg.beganov.CooksCorner.mapper.AppUserMapper;
import kg.beganov.CooksCorner.mapper.RecipeMapper;
import kg.beganov.CooksCorner.repository.AppUserFollowRepository;
import kg.beganov.CooksCorner.repository.AppUserRepository;
import kg.beganov.CooksCorner.service.AppUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppUserServiceImpl implements AppUserService {
    AppUserRepository appUserRepository;
    AppUserFollowRepository appUserFollowRepository;
    CloudinaryServiceImpl cloudinaryService;
    RecipeMapper recipeMapper;
    AppUserMapper appUserMapper;

    @Override
    public AppUser findById(Long id) {
        return appUserRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    @Override
    public UserDetailedView getUserByEmail(String email){
        AppUser user = appUserRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        return appUserMapper.mapUserToDetailedView(user);
    }
    @Override
    public UserDetailedView getUserById(Long id){
        return appUserMapper.mapUserToDetailedView(findById(id));
    }
    @Override
    public String uploadUserImage(MultipartFile file) {
        try{
            return cloudinaryService.uploadImage(file);
        }catch (IOException e){
            return "Failed to upload. "+e.getMessage();
        }
    }
    @Override
    public List<RecipePreview> getUserRecipesById(Long userId){
        AppUser user = findById(userId);
        return recipeMapper.mapRecipeToPreview(user.getRecipes());
    }
    @Override
    public void follow(Long followerId, Long followingId) {
        AppUser follower = findById(followerId);
        AppUser following = findById(followerId);
        AppUserFollow appUserFollow = new AppUserFollow();
        appUserFollow.setFollower(follower);
        appUserFollow.setFollowing(following);
        appUserFollow.setFollowingTime(LocalDateTime.now());
        appUserFollowRepository.save(appUserFollow);
    }
    @Override
    public void save(AppUser user){
        appUserRepository.save(user);
    }
    @Override
    public List<AppUser> findAppUsersByNameContaining(String query){
        return appUserRepository.findByNameContaining(query);
    }
}
