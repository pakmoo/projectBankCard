package ru.bank.card.lina.core.dto;

import java.util.List;

public record UserDTO (String username, String role, List<CardDTO> cards){
}
