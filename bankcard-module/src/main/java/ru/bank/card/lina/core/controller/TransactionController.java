package ru.bank.card.lina.core.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bank.card.lina.core.dto.TransactionResponse;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.service.TransactionService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public TransactionResponse transactionToCard(@NotNull @Validated Card cardFrom,
                                                 @NotNull @Validated Card cardTo,
                                                 @NotNull @Validated BigDecimal amount){
       return transactionService.transactionToCard(cardFrom, cardTo, amount);
    }



}
