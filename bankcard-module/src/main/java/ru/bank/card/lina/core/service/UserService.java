package ru.bank.card.lina.core.service;

import org.springframework.stereotype.Service;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public User findById(Long user_id){
        return userRepository.findById(user_id).get();
    }
    public void deleteUser(Long user_id){
        userRepository.deleteById(user_id);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
