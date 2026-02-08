package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.dto.UserRequest;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.mapping.UserMapper;
import ru.bank.card.lina.core.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //входящие данные
    @Transactional
    public UserDTO dtoToUser(UserRequest userRequest){
        User user = userMapper.dtoToUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.userToDTO(savedUser);
    }

    public UserDTO userToDTO(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return userMapper.userToDTO(user);
    }

    @Transactional
    public void deleteUser(Long user_id){
        if (!userRepository.existsById(user_id)){
            throw new RuntimeException("Пользователь не найден");
        }
        userRepository.deleteById(user_id);
    }
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(userMapper::userToDTO).collect(Collectors.toList());
    }
}
