package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.card.lina.core.dto.CardDTO;
import ru.bank.card.lina.core.dto.CardRequest;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.entity.Transaction;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.exeption.BalanceIsLessThanWithdrawAmountExeption;
import ru.bank.card.lina.core.mapping.CardMapper;
import ru.bank.card.lina.core.repository.CardRepository;
import ru.bank.card.lina.core.repository.TransactionRepository;
import ru.bank.card.lina.core.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    TransactionRepository transactionRepository;
    CardRepository cardRepository;
    UserRepository userRepository;
    CardMapper cardMapper;

    public CardService(CardRepository cardRepository, UserRepository userRepository, CardMapper cardMapper, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.cardMapper = cardMapper;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    //создание новой карты для пользователя
    public CardDTO saveCard(CardRequest cardRequest){
        User owner = userRepository.findById(cardRequest.getUser_Id()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Card card = new Card();
        card.setCardNumber(cardRequest.getCardNumber());
        card.setBalance(cardRequest.getBalance());
        card.setExpirationDate(cardRequest.getExpirationDate());
        card.setCardStatus(cardRequest.getCardStatus());
        card.setOwner(owner);
        Card savedCard= cardRepository.save(card);
        return cardMapper.cardToCardDTO(savedCard);
    }

    public CardDTO findById(Long card_id){
        return cardRepository.findById(card_id).map(cardMapper::cardToCardDTO).orElseThrow(()->new RuntimeException("карточка по id не найдена"));
    }

    public List<CardDTO> findAll(){
        return cardRepository.findAll().stream().map(cardMapper::cardToCardDTO).collect(Collectors.toList());
    }
    //поиск всех карт конкретного пользователя
    public List<CardDTO> findAllUserCard(Long user_id){
        return cardRepository.findAllByOwnerId(user_id).stream().map(cardMapper::cardToCardDTO).collect(Collectors.toList());
    }

    @Transactional
    public void deleteCard(Long card_id){
        if (!cardRepository.existsById(card_id)){
            throw new RuntimeException("Не удалось удалить: карта с id" + card_id + "не найдена");
        }
        cardRepository.deleteById(card_id);
    }

    @Transactional
    public CardDTO depositToCard(Long card_id, BigDecimal amount){
        Card card = cardRepository.findById(card_id).orElseThrow(() -> new RuntimeException("Карта не найдена."));
        card.setBalance(card.getBalance().add(amount));
        Transaction depositTrans = new Transaction();
        depositTrans.setName("Пополнение счёта");
        depositTrans.setAmount(amount);
        depositTrans.setTime(LocalDateTime.now());
        depositTrans.setCard(card);
        transactionRepository.save(depositTrans);
        return cardMapper.cardToCardDTO(cardRepository.save(card));
    }

    @Transactional
    public CardDTO withdrawFromCard(Long card_id, BigDecimal amount){
        Card card = cardRepository.findById(card_id).orElseThrow(() -> new RuntimeException("Карта не найдена."));
        if (card.getBalance().compareTo(amount) > 0){
            card.setBalance((card.getBalance().subtract(amount)));
            Transaction withdrawTrans = new Transaction();
            withdrawTrans.setName("Снятие наличных");
            withdrawTrans.setAmount(amount);
            withdrawTrans.setTime(LocalDateTime.now());
            withdrawTrans.setCard(card);
            transactionRepository.save(withdrawTrans);
            return cardMapper.cardToCardDTO(cardRepository.save(card));
        }
        else {throw new BalanceIsLessThanWithdrawAmountExeption();}

    }
}
