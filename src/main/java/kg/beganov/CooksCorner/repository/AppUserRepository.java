package kg.beganov.CooksCorner.repository;

import kg.beganov.CooksCorner.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.isEmailVerified = TRUE WHERE a.email = ?1")
    void enableAppUser(String email);

    boolean existsByEmail(String email);
}
