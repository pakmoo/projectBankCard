package ru.bank.card.lina.core.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.bank.card.lina.core.dto.CardDTO;
import ru.bank.card.lina.core.entity.Card;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface CardMapper {
    @Mapping(source = "cardNumber", target = "cardNum")
    @Mapping(source = "listTransaction", target = "transactions")
    CardDTO cardToCardDTO(Card card);
}
