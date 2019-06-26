
package com.example.lab7.mapper;

import com.example.lab7.dto.CreateUserDTO;
import com.example.lab7.dto.UpdateUserDTO;
import com.example.lab7.dto.UserDTO;
import com.example.lab7.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

    public List<UserDTO> toDTOs(List<User> users) {
        return users.stream()
                .map(user -> toDTO(user))
                .collect(Collectors.toList());
    }

    public User fromDTO(CreateUserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        return user;
    }

    public User fromDTO(UpdateUserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        return user;
    }
}
