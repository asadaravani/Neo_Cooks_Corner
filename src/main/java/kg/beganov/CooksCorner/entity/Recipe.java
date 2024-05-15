package kg.beganov.CooksCorner.entity;

import jakarta.persistence.*;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.enums.Difficulty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
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

    @Column @Enumerated(EnumType.STRING)
    Category category;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Ingredient> ingredients;

    @Column
    BigDecimal likes;

    @Column
    BigDecimal saves;

}
