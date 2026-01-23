package ru.bank.card.lina.core.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.bank.card.lina.core.dto.CardDTO;
import ru.bank.card.lina.core.entity.Card;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
    @Mapping(source = "cardNumber", target = "cardNum")
    CardDTO cardToCardDTO(Card card);
}
