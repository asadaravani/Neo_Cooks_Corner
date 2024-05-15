package kg.beganov.CooksCorner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Ingredient extends BaseEntity{

    @Column(nullable = false)
    String key;

    @Column(nullable = false)
    String value;

    @ManyToOne @JoinColumn
    Recipe recipe;
}
