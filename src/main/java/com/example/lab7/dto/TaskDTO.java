
package com.example.lab7.dto;

import com.example.lab7.enumType.TaskStatus;
import com.example.lab7.enumType.TaskType;
import java.time.LocalDate;


public class TaskDTO { //c
    private long id;
    private String title;
    private LocalDate createDate;
    private TaskStatus status;
    private TaskType type;

    private UserDTO user;

    public TaskDTO() {
    }

    public TaskDTO(long id, String title, LocalDate createDate, TaskStatus status, TaskType type, UserDTO user) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.status = status;
        this.type = type;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public UserDTO getUser() { // d i
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
