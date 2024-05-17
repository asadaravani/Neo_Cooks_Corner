package kg.beganov.CooksCorner.repository;

import kg.beganov.CooksCorner.entity.AppUserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserFollowRepository extends JpaRepository<AppUserFollow, Long> {

}
