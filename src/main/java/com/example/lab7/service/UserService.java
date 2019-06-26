/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lab7.service;

import com.example.lab7.entity.User;
import com.example.lab7.repository.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Darek
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public User getUser(long id) {
        return userRepository.getOne(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
    
    @PostConstruct
    private void exampleUsers()
    {
        User user=new User();
        user.setId(1);
        user.setName("Adam");
        createUser(user);
        
        User user2=new User();
        user2.setId(2);
        user2.setName("Tomek");
        createUser(user2);
    }
}
