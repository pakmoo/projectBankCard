package ru.bank.card.lina.core.dto;

import ru.bank.card.lina.core.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    String transactionName;
    LocalDateTime timeTransfer;
    BigDecimal amount;
    String descriptionTransaction;



    public TransactionResponse(String transactionName, LocalDateTime timeTransfer, BigDecimal amount, String descriptionTransaction ) {
        this.transactionName = transactionName;
        this.timeTransfer = timeTransfer;
        this.amount = amount;
        this.descriptionTransaction = descriptionTransaction;
    }

    public TransactionResponse() {
    }
    public String getDescriptionTransaction() {
        return descriptionTransaction;
    }

    public void setDescriptionTransaction(String descriptionTransaction) {
        this.descriptionTransaction = descriptionTransaction;
    }
    public String getTransactionName() {
        return transactionName;
    }

    public LocalDateTime getTimeTransfer() {
        return timeTransfer;
    }

    public BigDecimal getAmount() {
        return amount;
    }


}
