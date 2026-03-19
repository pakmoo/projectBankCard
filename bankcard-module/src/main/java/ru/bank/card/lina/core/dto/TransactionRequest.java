package ru.bank.card.lina.core.dto;

import java.math.BigDecimal;
//запрос в бд, преобразуем DTO.
//purpose - вид операции(пополнение, перевод, снятие), amount - сумма, toCardNumber - номер карты для перевода
public record TransactionRequest (String purpose, BigDecimal amount, Long fromCardId, Long toCardId) {
}
