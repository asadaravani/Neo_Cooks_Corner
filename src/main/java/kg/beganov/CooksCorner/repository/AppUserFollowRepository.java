package kg.beganov.CooksCorner.repository;

import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.AppUserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserFollowRepository extends JpaRepository<AppUserFollow, Long> {
    Optional<AppUserFollow> findByFollowerAndFollowing(AppUser follower, AppUser following);
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
