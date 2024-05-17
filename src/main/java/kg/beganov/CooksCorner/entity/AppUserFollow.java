package kg.beganov.CooksCorner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AppUserFollow extends BaseEntity{

    @ManyToOne
    AppUser follower;

    @ManyToOne
    AppUser following;

    @Column(nullable = false)
    LocalDateTime followingTime;

}
