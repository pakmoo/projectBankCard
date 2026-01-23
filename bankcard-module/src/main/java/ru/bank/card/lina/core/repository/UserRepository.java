package ru.bank.card.lina.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.card.lina.core.entity.User;

public interface UserRepository extends JpaRepository <User, Long> {
}
