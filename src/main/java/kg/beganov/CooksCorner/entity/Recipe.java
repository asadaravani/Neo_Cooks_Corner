package kg.beganov.CooksCorner.entity;

import jakarta.persistence.*;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.enums.Difficulty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Recipe extends BaseEntity{

    @Column
    String name;

    @Column
    String imagePath;

    @Column
    String description;

    @Column
    String preparationTime;

    @Column @Enumerated(EnumType.STRING)
    Difficulty difficulty;

    @ManyToOne @JoinColumn(nullable = false, name = "author")
    AppUser appUser;

    @ManyToMany(mappedBy = "savedRecipes", fetch = FetchType.EAGER)
    List<AppUser> savedByUsers;

    @ManyToMany(mappedBy = "likedRecipes", fetch = FetchType.EAGER)
    List<AppUser> likedByUsers;

    @Column @Enumerated(EnumType.STRING)
    Category category;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Ingredient> ingredients;


}
