package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.mapping.UserMapper;
import ru.bank.card.lina.core.repository.UserRepository;

@Service
public class TestService {
    UserRepository userRepository;

    public TestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO userToDTO(User owner){
        return UserMapper.INSTANCE.userToDto(owner);

    }
    public User dtoToUser(UserDTO userDTO){
        return userRepository.save(UserMapper.INSTANCE.dtoToUser(userDTO));
    }

}
