package ru.bank.card.lina.core.dto;

import ru.bank.card.lina.core.entity.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CardDTO(String cardNum, CardStatus cardStatus, BigDecimal balance, LocalDate expirationDate) {

}
