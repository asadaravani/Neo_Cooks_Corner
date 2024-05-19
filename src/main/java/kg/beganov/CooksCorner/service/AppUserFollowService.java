package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.AppUserFollow;

public interface AppUserFollowService {
    AppUserFollow findByFollowerAndFollowing(AppUser follower, AppUser following);

    boolean isExistByFollowerIdAndFollowingId(Long followerId, Long followingId);

    void save(AppUserFollow appUserFollow);

    void delete(AppUserFollow appUserFollow);
}
