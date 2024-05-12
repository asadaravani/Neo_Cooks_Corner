package kg.beganov.CooksCorner.entity;

import jakarta.persistence.*;
import kg.beganov.CooksCorner.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AppUser extends BaseEntity implements UserDetails {

    @Column
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Column
    String avatarPath;

    @Column
    String bio;

    @OneToMany(mappedBy = "appUser")
    List<Recipe> recipes;

    @OneToMany(mappedBy = "appUser")
    List<ConfirmationToken> confirmationTokens;

    @Column @Enumerated(EnumType.STRING)
    Role role;

    @OneToOne
    AppUserFollow appUserFollow;

    @Column
    boolean isEmailVerified;

    public AppUser(){
        super();
        this.isEmailVerified = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
