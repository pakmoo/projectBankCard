package ru.bank.card.lina.core.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.bank.card.lina.core.dto.TransactionDTO;
import ru.bank.card.lina.core.dto.TransactionRequest;
import ru.bank.card.lina.core.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    //входящие данные
    @Mapping(source = "purpose", target = "name")
    Transaction dtoToTransaction(TransactionRequest transactionRequest);

    //выходящие данные
    @Mapping(source = "name", target = "transactionName")
    @Mapping(source = "time", target = "timeTransfer")
    TransactionDTO transactionToDTO(Transaction transaction);

}
