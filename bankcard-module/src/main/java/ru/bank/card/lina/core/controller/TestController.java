package ru.bank.card.lina.core.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bank.card.lina.core.dto.UserDTO;
import ru.bank.card.lina.core.entity.User;
import ru.bank.card.lina.core.service.TestService;

@RequestMapping
@RestController
public class TestController {
    TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
    @PostMapping
    public UserDTO userToDTO(@RequestBody User owner){
        return this.testService.userToDTO(owner);
    }

    @PostMapping("/saveUser")
    public User dtoToUser(@RequestBody UserDTO userDTO){return this.testService.dtoToUser(userDTO);}

}
