package ru.bank.card.lina.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.bank.card.lina.core.entity.enums.CardStatus;
import ru.bank.card.lina.core.entity.enums.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,
            orphanRemoval = true)

    private Set<Card> cards = new HashSet<>();

}
