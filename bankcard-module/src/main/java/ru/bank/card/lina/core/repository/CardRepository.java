package ru.bank.card.lina.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.card.lina.core.entity.Card;
import ru.bank.card.lina.core.entity.User;

import java.util.List;

public interface CardRepository extends JpaRepository <Card, Long>  {
    List<Card> findAllByOwnerId(Long ownerid);
}
