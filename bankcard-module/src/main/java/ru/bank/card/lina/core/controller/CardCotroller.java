package ru.bank.card.lina.core.controller;

import org.springframework.web.bind.annotation.*;
import ru.bank.card.lina.core.dto.CardRequest;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.service.CardService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardCotroller {
    private CardService cardService;

    public CardCotroller(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/save")
    public void saveCard(@RequestBody CardRequest card){
        cardService.saveCard(card);
    }

    @GetMapping("/{id}")
    public Card findById(@RequestParam Long card_id){
        return cardService.findById(card_id);
    }

    @DeleteMapping("/delete")
    public void deleteCard(@RequestParam Long card_id){
        cardService.deleteCard(card_id);
    }

    @GetMapping("/findAll")
    public List<Card> findAll(){
        return cardService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Card> findAllUserCard(@RequestParam Long user_id){
        return cardService.findAllUserCard(user_id);
    }

    @PostMapping("/deposit")
    public Card depositToCard(@RequestParam Long card_id, @RequestParam BigDecimal amount){
        return cardService.depositToCard(card_id, amount);
    }

    @PostMapping("/withdraw")
    public Card withdrawFromCard(@RequestParam Long card_id, @RequestParam BigDecimal amount){
        return cardService.withdrawFromCard(card_id, amount);
    }
}
