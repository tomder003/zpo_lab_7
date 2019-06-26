
package com.example.lab7.controller;

import com.example.lab7.dto.CreateUserDTO;
import com.example.lab7.dto.UpdateUserDTO;
import com.example.lab7.dto.UserDTO;
import com.example.lab7.entity.User;
import com.example.lab7.mapper.UserMapper;
import com.example.lab7.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        List<User> users = userService.getUsers();
        return userMapper.toDTOs(users);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUser(@PathVariable long userId) {
        User user = userService.getUser(userId);
        return userMapper.toDTO(user);
    }

    @PostMapping("/users")
    public void createUser(CreateUserDTO userDTO) {
        User user = userMapper.fromDTO(userDTO);
        userService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    public void updateUser(UpdateUserDTO userDTO, @PathVariable long userId) {
        User userFromDb = userService.getUser(userId);
        if (userFromDb != null) {
            User user = userMapper.fromDTO(userDTO);
            user.setId(userId);
            userService.updateUser(user);
        }
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}   
