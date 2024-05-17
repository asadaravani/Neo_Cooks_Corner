package kg.beganov.CooksCorner.repository;

import kg.beganov.CooksCorner.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findUserByEmail(String email);
    List<AppUser> findByNameContaining(String query);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.isEmailVerified = TRUE WHERE a.email = ?1")
    void enableAppUser(String email);

    boolean existsByEmail(String email);
}
