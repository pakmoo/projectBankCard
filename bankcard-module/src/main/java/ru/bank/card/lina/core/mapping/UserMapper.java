package ru.bank.card.lina.core.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToDto(User user);
}

