package kg.beganov.CooksCorner.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.beganov.CooksCorner.dto.request.AppUserEditRequest;
import kg.beganov.CooksCorner.dto.response.RecipePreview;
import kg.beganov.CooksCorner.dto.response.UserDetailedView;
import kg.beganov.CooksCorner.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserService appUserService;

    @Operation(summary = "Get User by Email, detailed view")
    @GetMapping("/byEmail/{email}")
    public UserDetailedView getUserByEmail(@PathVariable String email) {
        return appUserService.getUserByEmail(email);
    }

    @Operation(summary = "Get User by ID, detailed view")
    @GetMapping("/byId/{id}")
    public UserDetailedView getUserByEmail(@PathVariable Long id) {
        return appUserService.getUserById(id);
    }

    @Operation(summary = "Follow/Unfollow User2User")
    @PostMapping("/follow")
    public void follow(@RequestParam("followerId") Long followerId,
                       @RequestParam("followingId") Long followingId) {
        appUserService.followAppUser2AppUser(followerId, followingId);
    }

    @Operation(summary = "Check if User is following User")
    @GetMapping("/isFollowing")
    public boolean isUserFollowingUser(@RequestParam("followerId") Long followerId,
                                       @RequestParam("followingId") Long followingId){
        return appUserService.isAppUserFollowing2AppUser(followerId, followingId);
    }

    @Operation(summary = "Upload Image, max size : 1MB")
    @PostMapping(value = "/uploadUserImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadUserImage(@RequestParam("file") MultipartFile file) throws IOException {
        return appUserService.uploadUserImage(file);
    }

    @Operation(summary = "Get Recipes by UserID ")
    @GetMapping("/{id}/recipes")
    public List<RecipePreview> getUserRecipesById(@PathVariable Long id){
        return appUserService.getUserRecipesById(id);
    }

    @Operation(summary = "Get saved recipes of USER ")
    @GetMapping("/{id}/saved")
    public List<RecipePreview> getSavedRecipesById(@PathVariable Long id){
        return appUserService.getSavedRecipesById(id);
    }

    @Operation(summary = "Manage profile")
    @PutMapping("/{userId}/edit")
    public String editAppUserProfile(@PathVariable Long userId, @RequestBody AppUserEditRequest editRequest){
        return appUserService.editAppUserProfile(userId, editRequest);
    }
}
