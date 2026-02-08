package ru.bank.card.lina.core.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.dto.UserRequest;
import ru.bank.card.lina.core.entity.User;

@Mapper(componentModel = "spring", uses = {CardMapper.class})
public interface UserMapper {
    //из входных данных в бд
    User dtoToUser(UserRequest userRequest);
    //выходящие данные
    UserDTO userToDTO(User user);
}

