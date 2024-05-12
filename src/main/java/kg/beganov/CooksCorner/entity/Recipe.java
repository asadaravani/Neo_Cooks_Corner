package kg.beganov.CooksCorner.entity;

import jakarta.persistence.*;
import kg.beganov.CooksCorner.enums.Category;
import kg.beganov.CooksCorner.enums.Difficulty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @Column
    String ingredients;

    @Column
    BigDecimal likes;

    @Column
    BigDecimal saves;

}
