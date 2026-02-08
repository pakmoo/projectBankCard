package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.card.lina.core.dto.TransactionDTO;
import ru.bank.card.lina.core.dto.TransactionRequest;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.entity.Transaction;
import ru.bank.card.lina.core.exeption.BalanceIsLessThanAmountExeption;
import ru.bank.card.lina.core.mapping.TransactionMapper;
import ru.bank.card.lina.core.repository.CardRepository;
import ru.bank.card.lina.core.repository.TransactionRepository;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    CardRepository cardRepository;
    TransactionRepository transactionRepository;
    UserService userService;
    TransactionMapper transactionMapper;


    public TransactionService(CardRepository cardRepository, TransactionRepository transactionRepository,
                              UserService userService, TransactionMapper transactionMapper) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.transactionMapper = transactionMapper;
    }

    public TransactionDTO dtoToTransaction(TransactionRequest transactionRequest){
        Transaction transaction = transactionMapper.dtoToTransaction(transactionRequest);
        transaction.setTime(LocalDateTime.now());
        return transactionMapper.transactionToDTO(transactionRepository.save(transaction));

    }

    @Transactional
    public TransactionDTO transactionToCard(TransactionRequest transactionRequest){
        //ищем карты по id из входных данных
        Card cardFrom = cardRepository.findById(transactionRequest.fromCardId()).orElseThrow(() -> new RuntimeException("Карта с таким id не найдена."));
        Card cardTo = cardRepository.findById(transactionRequest.toCardId()).orElseThrow(() -> new RuntimeException("Карта с таким id не найдена"));
        //проверка баланса
        if (cardFrom.getBalance().compareTo(transactionRequest.amount()) >= 0){
            //смена балансов
            cardFrom.setBalance(cardFrom.getBalance().subtract(transactionRequest.amount()));
            cardTo.setBalance(cardTo.getBalance().add(transactionRequest.amount()));

            //транзакция для отправителя
            Transaction newTransactionFrom = new Transaction();
            newTransactionFrom.setName("перевод с: "+ cardFrom.getCardNumber() + " на "+cardTo.getCardNumber());
            newTransactionFrom.setCard(cardFrom);
            newTransactionFrom.setTime(LocalDateTime.now());
            newTransactionFrom.setAmount(transactionRequest.amount());
            cardFrom.getListTransaction().add(newTransactionFrom);
            transactionRepository.save(newTransactionFrom);

            //транзакции для получателя
            Transaction newTransactionTo = new Transaction();
            newTransactionTo.setName("перевод "+ transactionRequest.amount());
            newTransactionTo.setCard(cardTo);//привязываем к карте получателя
            newTransactionTo.setTime(LocalDateTime.now());
            newTransactionTo.setAmount(transactionRequest.amount());
            cardTo.getListTransaction().add(newTransactionTo);
            transactionRepository.save(newTransactionTo);

            cardRepository.save(cardFrom);
            cardRepository.save(cardTo);
        }
        else {throw new BalanceIsLessThanAmountExeption();
        }
        return dtoToTransaction(transactionRequest);
    }
}
