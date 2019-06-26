
package com.example.lab7.dto;

import com.example.lab7.entity.User;


public class UpdateTaskUserDTO { //c
    private long id;
    private User user;

    public UpdateTaskUserDTO(long id, User user) { //d iii
        this.id = id;
        this.user = user;
    }

    public UpdateTaskUserDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
