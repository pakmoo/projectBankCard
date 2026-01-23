package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import ru.bank.card.lina.core.dto.CardRequest;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.exeption.BalanceIsLessThanWithdrawAmountExeption;
import ru.bank.card.lina.core.repository.CardRepository;
import ru.bank.card.lina.core.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {
    CardRepository cardRepository;
    UserRepository userRepository;

    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public void saveCard(CardRequest cardRequest){
        Card card = new Card();
        card.setCardNumber(cardRequest.getCardNumber());
        card.setBalance(cardRequest.getBalance());
        card.setExpirationDate(cardRequest.getExpirationDate());
        card.setListTransaction(cardRequest.getListTransaction());
        card.setOwner(userRepository.findById(cardRequest.getUser_Id()).orElseThrow());
        cardRepository.save(card);
    }
    public void saveCard(Card card){
        cardRepository.save(card);
    }

    public Card findById(Long card_id){
        return cardRepository.findById(card_id).orElseThrow(()->new RuntimeException("карточка по id не найдена"));
    }
    public void deleteCard(Long card_id){
        cardRepository.deleteById(card_id);
    }
    public List<Card> findAll(){
        return cardRepository.findAll();
    }
    public List<Card> findAllUserCard(Long user_id){
        return cardRepository.findByOwnerId(user_id);
    }
    public Card depositToCard(Long card_id, BigDecimal amount){
        Card card = findById(card_id);
        card.setBalance(card.getBalance().add(amount));
        saveCard(card);
        return card;
    }

    public Card withdrawFromCard(Long card_id, BigDecimal amount){
        Card card = findById(card_id);
        if (card.getBalance().compareTo(amount) > 0){
            card.setBalance((card.getBalance().subtract(amount)));
            return card;
        }
        else {throw new BalanceIsLessThanWithdrawAmountExeption();}

    }
}
