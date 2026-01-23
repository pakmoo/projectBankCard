package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.mapping.UserMapper;

@Service
public class TestService {
    public UserDTO userToDTO(User owner){
        return UserMapper.INSTANCE.userToDto(owner);

    }
}
