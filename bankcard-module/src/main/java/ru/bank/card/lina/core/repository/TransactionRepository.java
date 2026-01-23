package ru.bank.card.lina.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.card.lina.core.entity.Transaction;

public interface TransactionRepository extends JpaRepository <Transaction, Long> {
}
