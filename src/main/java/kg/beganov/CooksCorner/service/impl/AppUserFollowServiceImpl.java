package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.AppUserFollow;
import kg.beganov.CooksCorner.exception.NoSuchRelationshipException;
import kg.beganov.CooksCorner.repository.AppUserFollowRepository;
import kg.beganov.CooksCorner.service.AppUserFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserFollowServiceImpl implements AppUserFollowService {
    private final AppUserFollowRepository appUserFollowRepository;

    @Override
    public AppUserFollow findByFollowerAndFollowing(AppUser follower, AppUser following){
        return appUserFollowRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(NoSuchRelationshipException::new);
    }
    @Override
    public boolean isExistByFollowerIdAndFollowingId(Long followerId, Long followingId){
        return appUserFollowRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
    @Override
    public void save(AppUserFollow appUserFollow){
        appUserFollowRepository.save(appUserFollow);
    }
    @Override
    @Transactional
    public void delete(AppUserFollow appUserFollow){
        appUserFollowRepository.deleteById(appUserFollow.getId());
    }
}
