package ru.bank.card.lina.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.bank.card.lina.core.entity.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Номер карты не может быть пустым.")
    @Size(min = 16, max = 16, message = "Номер карты должен содержать ровно 16 символов.")
    private String cardNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
    private LocalDate expirationDate;
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    private BigDecimal balance;
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List <Transaction> ListTransaction;
}
