package ru.bank.card.lina.core.controller;

import org.springframework.web.bind.annotation.*;
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
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User findById(@RequestParam Long user_id){
        return userService.findById(user_id);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long user_id){
        userService.deleteUser(user_id);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

}
