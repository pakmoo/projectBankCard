package ru.bank.card.lina.core.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bank.card.lina.core.dto.TransactionResponse;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.entity.Transaction;
import ru.bank.card.lina.core.exeption.BalanceIsLessThanAmountExeption;
import ru.bank.card.lina.core.repository.CardRepository;
import ru.bank.card.lina.core.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    CardRepository cardRepository;
    TransactionRepository transactionRepository;
    UserService userService;

    public TransactionService(CardRepository cardRepository, TransactionRepository transactionRepository, UserService userService) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Transactional
    public TransactionResponse transactionToCard(@NotNull @Validated Card cardFrom,
                                                 @NotNull @Validated Card cardTo,
                                                 @NotNull @Validated BigDecimal amount){
        if (cardFrom.getBalance().compareTo(amount) > 0){
            cardFrom.setBalance(cardFrom.getBalance().subtract(amount));
            cardTo.setBalance(cardTo.getBalance().add(amount));
            Transaction newTransactionFrom = new Transaction();
            Transaction newTransactionTo = new Transaction();
            newTransactionFrom.setName("перевод с: "+ cardFrom + "на "+cardTo);
            newTransactionFrom.setCard(cardFrom);
            newTransactionFrom.setTime(LocalDateTime.now());
            cardFrom.getListTransaction().add(newTransactionFrom);
            newTransactionTo.setName("перевод "+amount);
            newTransactionTo.setCard(cardFrom);
            newTransactionTo.setTime(LocalDateTime.now());
            cardTo.getListTransaction().add(newTransactionTo);
        }
        else {throw new BalanceIsLessThanAmountExeption();
        }
        return (new TransactionResponse("Перевод", LocalDateTime.now(), amount, "Перевод выполнен" ));
    }
}
