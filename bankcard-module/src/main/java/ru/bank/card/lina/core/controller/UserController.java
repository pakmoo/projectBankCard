package ru.bank.card.lina.core.controller;

import org.springframework.web.bind.annotation.*;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.dto.UserRequest;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserRequest userRequest){
        return userService.dtoToUser(userRequest);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@RequestParam Long user_id){
        return userService.userToDTO(user_id);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long user_id){
        userService.deleteUser(user_id);
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

}
