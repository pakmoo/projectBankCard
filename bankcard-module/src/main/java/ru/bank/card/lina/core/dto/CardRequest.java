package ru.bank.card.lina.core.dto;

import ru.bank.card.lina.core.entity.Transaction;
import ru.bank.card.lina.core.entity.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CardRequest {
    private long user_id;
    private String cardNumber;
    private LocalDate expirationDate;
    private BigDecimal balance;
    private CardStatus cardStatus;
    //private List<Transaction> ListTransaction;

    public CardRequest(long user_id, String cardNumber, LocalDate expirationDate, BigDecimal balance, CardStatus cardStatus) {
        this.user_id = user_id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.balance = balance;
        this.cardStatus = cardStatus;
    }

    public long getUser_Id() {
        return user_id;
    }

    public void setUser_Id(long id) {
        this.user_id = user_id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
