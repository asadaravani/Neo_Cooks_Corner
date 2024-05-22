package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.dto.request.AppUserEditRequest;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.dto.response.UserDetailedView;
import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.AppUserFollow;
import kg.beganov.CooksCorner.exception.UserNotFoundException;
import kg.beganov.CooksCorner.mapper.AppUserMapper;
import kg.beganov.CooksCorner.mapper.RecipeMapper;
import kg.beganov.CooksCorner.repository.AppUserRepository;
import kg.beganov.CooksCorner.service.AppUserFollowService;
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
    AppUserFollowService appUserFollowService;
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
    public String editAppUserProfile(Long userId, AppUserEditRequest appUserEditRequest){
        AppUser user = findById(userId);
        appUserRepository.save(appUserMapper.mapEditRequestToAppUser(appUserEditRequest, user));
        return "Profile is edited successfully";
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
    public  List<RecipePreview> getSavedRecipesById(Long userId){
        AppUser user = findById(userId);
        return recipeMapper.mapRecipeToPreview(user.getSavedRecipes());
    }

    @Override
    public void followAppUser2AppUser(Long followerId, Long followingId) {
        AppUser follower = findById(followerId);
        AppUser following = findById(followingId);
        if(isAppUserFollowing2AppUser(followerId, followingId)){
            AppUserFollow appUserFollow = appUserFollowService.findByFollowerAndFollowing(follower, following);
            appUserFollowService.delete(appUserFollow);
        }
        else {
            AppUserFollow appUserFollow = new AppUserFollow();
            appUserFollow.setFollower(follower);
            appUserFollow.setFollowing(following);
            appUserFollow.setFollowingTime(LocalDateTime.now());
            appUserFollowService.save(appUserFollow);
        }
    }
    @Override
    public boolean isAppUserFollowing2AppUser(Long followerId, Long followingId){
        return appUserFollowService.isExistByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    public void save(AppUser user){
        appUserRepository.save(user);
    }
    @Override
    public List<AppUser> findAppUsersByNameContaining(String query){
        return appUserRepository.findByNameContainingIgnoreCase(query);
    }
}
